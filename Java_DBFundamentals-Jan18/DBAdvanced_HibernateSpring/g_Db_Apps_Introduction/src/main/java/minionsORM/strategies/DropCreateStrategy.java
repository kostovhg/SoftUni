package minionsORM.strategies;

import minionsORM.scanner.EntityScanner;
import minionsORM.strategies.tableCreator.TableCreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DropCreateStrategy extends SchemaInitializationStrategyImpl {

    private final String DROP_DATABASE_QUERY = "DROP DATABASE IF EXISTS `%s`;";
    private final String CREATE_DATABASE_QUERY = "CREATE DATABASE `%s`;";

    public DropCreateStrategy(EntityScanner entityScanner,
                              TableCreator creator,
                              Connection connection,
                              String dataSource){
        super(entityScanner, creator, connection, dataSource);
    }

    @Override
    public void execute() throws SQLException, ClassNotFoundException {
        String dropQuery = String.format(DROP_DATABASE_QUERY, super.dataSource);
        this.connection.prepareStatement(dropQuery).executeUpdate();

        String createQuery = String.format(CREATE_DATABASE_QUERY, super.dataSource);
        this.connection.prepareStatement(createQuery).execute();

        this.createTables(super.scanForEntities());
    }

    public void createTables(List<Class> entities) throws SQLException {
        for (Class entity : entities) {
            this.creator.doCreate(entity);
        }
    }
}
