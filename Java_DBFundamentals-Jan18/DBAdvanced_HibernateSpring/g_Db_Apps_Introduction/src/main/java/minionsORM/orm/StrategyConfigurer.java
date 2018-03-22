package minionsORM.orm;

import minionsORM.scanner.EntityScanner;
import minionsORM.strategies.SchemaInitializationStrategy;
import minionsORM.strategies.tableCreator.TableCreatorImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StrategyConfigurer {

    private EntityManagerBuilder builder;

    public StrategyConfigurer(EntityManagerBuilder builder){
        this.builder = builder;
    }

    public <T extends SchemaInitializationStrategy> EntityManagerBuilder set(Class<T> strategyClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<SchemaInitializationStrategy> constructor =
                (Constructor<SchemaInitializationStrategy>) strategyClass.getDeclaredConstructors()[0];
        SchemaInitializationStrategy strategy = constructor.newInstance(
                new EntityScanner(),
                new TableCreatorImpl(this.builder.getConnection(), this.builder.getDataSource()),
                this.builder.getConnection(),
                this.builder.getDataSource());
        this.builder.setStrategy(strategy);
        return this.builder;
    }
}
