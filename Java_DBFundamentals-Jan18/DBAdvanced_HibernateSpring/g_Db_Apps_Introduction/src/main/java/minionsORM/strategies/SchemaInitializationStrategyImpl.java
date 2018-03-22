package minionsORM.strategies;

import minionsORM.scanner.EntityScanner;
import minionsORM.strategies.tableCreator.TableCreator;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

public abstract class SchemaInitializationStrategyImpl implements SchemaInitializationStrategy {

    private EntityScanner entityScanner;
    TableCreator creator;
    Connection connection;
    String dataSource;

    public SchemaInitializationStrategyImpl(EntityScanner entityScanner,
                                            TableCreator creator,
                                            Connection connection,
                                            String dataSource) {
        this.entityScanner = entityScanner;
        this.creator = creator;
        this.connection = connection;
        this.dataSource = dataSource;
    }

    List<Class> scanForEntities() throws ClassNotFoundException {
        return this.entityScanner.getAllEntities(System.getProperty("user.dir"));
    }
}
