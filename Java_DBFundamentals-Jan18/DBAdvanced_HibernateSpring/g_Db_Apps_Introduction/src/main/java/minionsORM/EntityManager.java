package minionsORM;

import minionsORM.interfaces.DbContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class EntityManager implements DbContext {

    private Connection connection;
    private Set<Object> persistedEntities;

    public EntityManager(Connection connection) {
        this.connection = connection;
        this.persistedEntities = new HashSet<>();
    }

    @Override
    public <E> boolean persist(E entity) throws IllegalAccessException, SQLException {
        return false;
    }

    @Override
    public <E> Iterable<E> find(Class<E> table) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return null;
    }

    @Override
    public <E> Iterable<E> find(Class<E> table, String where) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        return null;
    }

    @Override
    public <E> E findFirst(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException {
        return null;
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        return null;
    }
}
