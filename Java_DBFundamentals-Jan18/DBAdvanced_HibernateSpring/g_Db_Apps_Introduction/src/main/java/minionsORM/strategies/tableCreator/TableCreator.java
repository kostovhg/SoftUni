package minionsORM.strategies.tableCreator;

import java.lang.reflect.Field;
import java.sql.SQLException;

public interface TableCreator {

    void doCreate(Class entity) throws SQLException;

    String getFieldName(Field field);

    String getTableName(Class<?> entity);

    String getDatabaseType(Field field);
}
