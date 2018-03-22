package minionsORM.orm;

import minionsORM.annotations.Column;
import minionsORM.annotations.Entity;
import minionsORM.annotations.Id;
import minionsORM.interfaces.DbContext;
import minionsORM.strategies.SchemaInitializationStrategy;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static minionsORM.utilities.Constants.DB_NAME;

public class EntityManager implements DbContext {

    private static final String INSERT_QUERY = "INSERT INTO %s(%s) VALUES(%s);";
    private static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s";
    private static final String DELETE_QUERY = "DELETE FROM %s WHERE 1 %s LIMIT 1;";
    private static final String SELECT_QUERY = "SELECT * FROM %s WHERE 1 %s %s;";

    private Connection connection;
    private String dataSource;
    private Set<Object> persistedEntities;
    private SchemaInitializationStrategy strategy;

    public EntityManager(Connection connection, String dataSource, SchemaInitializationStrategy strategy) throws SQLException, ClassNotFoundException {
        this.connection = connection;
        this.dataSource = dataSource;
        this.strategy = strategy;
        this.persistedEntities = new HashSet<>();
        this.strategy.execute();
    }

    @Override
    public <E> boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary = this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value = primary.get(entity);

        if(value == null || (long) value < 0){
            return this.doInsert(entity, primary);
        }
        return this.doUpdate(entity, primary);
    }


    @Override
    public <E> Iterable<E> find(Class<E> table) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String query = String.format(SELECT_QUERY, this.getTableName(table), "", "");
        ResultSet rs = connection.prepareStatement(query).executeQuery();

        List<E> entities = new ArrayList<>();
        while (rs.next()){
            E entity = table.newInstance();
            this.fillEntity(table, rs, entity);
            entities.add(entity);
        }
        return null;
    }

    @Override
    public <E> Iterable<E> find(Class<E> table, String where) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String query = String.format(SELECT_QUERY,
                this.getTableName(table), (where != null ? " AND " + where : ""), "");
        ResultSet rs = connection.prepareStatement(query).executeQuery();

        List<E> entities = new ArrayList<>();
        while(rs.next()){
            E entity = table.newInstance();
            this.fillEntity(table, rs, entity);
            entities.add(entity);
        }

        return entities;
    }

    @Override
    public <E> E findFirst(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException {
        String query = String.format(SELECT_QUERY, this.getTableName(table), "", "");
        ResultSet rs = connection.prepareStatement(query).executeQuery();
        E entity = table.newInstance();
        if(rs.next()){
            this.fillEntity(table, rs, entity);
        }
        return entity;
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        String query = String.format(SELECT_QUERY,
                this.getTableName(table), (where != null ? " AND " + where : ""), "LIMIT 1");
        ResultSet rs = connection.prepareStatement(query).executeQuery();
        E entity = table.newInstance();
        if(rs.next()){
            this.fillEntity(table, rs, entity);
        }
        return entity;
    }

    private <E> boolean doCreate(E entity, Field primary){
        String query = "CREATE TABLE IF NOT EXISTS ";
        // implement
        return true;
    }

    private String getDbType(Field field, Field primary) {
        field.setAccessible(true);
        if(field.getName().equals(primary.getName())){
            return "BIGINT PRIMARY KEY AUTO_INCREMENT";
        }

        switch (field.getType().getSimpleName()){
            case "int":
                return "INT";
            case "Integer":
                return "INT";
            case "String":
                return "VARCHAR(50)";
            case "Long":
                return "INT";
            //case "Date":
              //  return "DATETIME";
        }
        return null;
    }

    private Field getId(Class entity) {
        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(
                        () -> new UnsupportedOperationException("Entity does not have a primary key.")
                );
    }

    private String getTableName(Class<?> entityClass) {
        if(entityClass.isAnnotationPresent(Entity.class)){
            return DB_NAME + "." + entityClass.getAnnotation(Entity.class).name();
        }
        throw new UnsupportedOperationException("Entity does not exist.");
    }

    private <E> boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());

        Field[] fields = entity.getClass().getDeclaredFields();

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : fields){
            field.setAccessible(true);
            if(!field.getName().equals(primary.getName()) && field.isAnnotationPresent(Column.class)){
                columns.add("`" + field.getAnnotation(Column.class).name() + "`");

                Object value = field.get(entity);
                if(isNum(field)){
                    values.add(value.toString());
                } else {
                    values.add("\'" + value.toString() + "\'");
                }
            }
        }

        String query = String.format(
                INSERT_QUERY, tableName,
                String.join(", ", columns),
                String.join(", ", values));

        boolean result = connection.prepareStatement(query).execute();
        String q = "SELECT LAST_INSERT_ID() AS id;";
        ResultSet rs = connection.prepareStatement(q).executeQuery();
        rs.next();
        primary.set(entity, rs.getInt("id"));

        return result;
    }

    private boolean isNum(Field field) {
        return field.getType() == int.class || field.getType() == Integer.class
                || field.getType() == double.class || field.getType() == Double.class;
    }

    private <E> boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        Field[] fields = entity.getClass().getDeclaredFields();
        String where = "";

        List<String> values = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.getName().equals(primary.getName())){
                where += field.getName() + " = " + field.get(entity).toString();
            } else if(field.isAnnotationPresent(Column.class)){
                String str = "`" + field.getAnnotation(Column.class).name() + "` = ";

                Object value = field.get(entity);
                str += isNum(field) ? value.toString() : "\'" + value.toString() + "\'";
                values.add(str);
            }
        }

        String query = String.format(UPDATE_QUERY, tableName, String.join(", ", values), where);
        return connection.prepareStatement(query).execute();
    }

    public boolean doDelete(Class<?> table, String where) throws SQLException {
        String tableName = this.getTableName(table);

        String query = String.format(DELETE_QUERY,
                tableName, (where != null ? " AND " + where : ""));
        return connection.prepareStatement(query).execute();
    }

    private <E> void fillEntity(Class<E> table, ResultSet rs, E entity) throws SQLException, IllegalAccessException {
        Field[] fields = table.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            this.fillField(field, entity, rs, field.getAnnotation(Column.class).name());
        }
    }

    private <E> void fillField(Field field, E entity, ResultSet rs, String fieldName) throws SQLException, IllegalAccessException {
        if(field.getType() == int.class || field.getType() == Integer.class){
            field.set(entity, rs.getInt(fieldName));
        } else if (field.getType() == String.class){
            field.set(entity, rs.getString(fieldName));
        } else if(field.getType() == double.class || field.getType() == Double.class){
            field.set(entity, rs.getDouble(fieldName));
        }
    }
}
