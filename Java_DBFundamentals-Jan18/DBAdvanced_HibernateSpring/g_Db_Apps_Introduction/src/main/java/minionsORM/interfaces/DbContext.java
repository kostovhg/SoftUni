package minionsORM.interfaces;


import java.sql.SQLException;

public interface DbContext {

    <E> boolean persist(E entity) throws IllegalAccessException, SQLException;

    <E> Iterable<E> find(Class<E> table)
        throws  ClassNotFoundException,SQLException, InstantiationException, IllegalAccessException;

    <E> Iterable<E> find(Class<E> table, String where)
        throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;

    <E> E findFirst(Class<E> table)
        throws IllegalAccessException, SQLException, InstantiationException;

    <E> E findFirst(Class<E> table, String where)
        throws SQLException, IllegalAccessException, InstantiationException;
}
