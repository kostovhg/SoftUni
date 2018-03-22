package minionsORM.strategies;

import minionsORM.annotations.Column;
import minionsORM.annotations.Entity;
import minionsORM.scanner.EntityScanner;
import minionsORM.strategies.tableCreator.TableCreator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateStrategy extends SchemaInitializationStrategyImpl {

    private final String ALTER_TABLE_QUERY = "ALTER TABLE %s ";
    private final String ADD_COLUMN_QUERY = "ADD COLUMN `%s` $s;";

    public UpdateStrategy(EntityScanner entityScanner,
                          TableCreator creator,
                          Connection connection,
                          String dataSource) {
        super(entityScanner, creator, connection, dataSource);
    }

    @Override
    public void execute() throws SQLException, ClassNotFoundException {
        List<Class> entities = this.scanForEntities();

        for (Class<?> entity : entities) {
            if (!checkIfTableExists(entity.getAnnotation(Entity.class).name())) {
                this.creator.doCreate(entity);
            } else {
                doAlter(entity);
            }
        }
    }

    public void doAlter(Class entity) throws SQLException {
        String tableName = this.getTableName(entity);
        String query = String.format(ALTER_TABLE_QUERY, this.dataSource + "." + tableName);

        Field[] fields = entity.getDeclaredFields();

        List<String> coluns = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class) && !checkIfFieldExists(tableName, field)) {
                String column = String.format(ADD_COLUMN_QUERY,
                        field.getAnnotation(Column.class).name(),
                        this.getDatabaseType(field));
            }
        }
    }

    private boolean checkIfFieldExists(String tableName, Field field) throws SQLException {
        String fieldName = field.getAnnotation(Column.class).name();

        String query = String.format("SELECT * " +
                "FROM information_schema.COLUMNS " +
                "WHERE TABLE_SCHEMA = '%s' " +
                "AND TABLE_NAME = '%s' " +
                "AND COLUMN_NAME = '%s'", "minionsDB" , tableName, fieldName);

        return connection.prepareStatement(query).executeQuery().next();

    }

    private boolean checkIfTableExists(String name) throws SQLException {
        String query = String.format("SELECT table_name " +
        "FROM information_schema.TABLES " +
        "WHERE TABLE_SCHEMA = '%s' " +
        "AND TABLE_NAME = '%s'", "MinionsDB", name);

        return connection.prepareStatement(query).executeQuery().next();
    }

    public String getTableName(Class<?> entity) {
        if(entity.isAnnotationPresent(Entity.class)) {
            return entity.getAnnotation(Entity.class).name();
        }
        throw new UnsupportedOperationException("Entity does not exist.");
    }

    public String getDatabaseType(Field field) {
        field.setAccessible(true);
        switch (field.getType().getSimpleName()){
            case "int":
            case "Integer":
                return "INT";
            case "String":
                return "VARCHAR(50)";
            case "Date":
                return "DATETIME";
        }
        return null;
    }
}
