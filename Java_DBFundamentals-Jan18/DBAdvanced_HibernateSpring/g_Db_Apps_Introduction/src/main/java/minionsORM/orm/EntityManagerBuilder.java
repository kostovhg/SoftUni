package minionsORM.orm;

import minionsORM.strategies.SchemaInitializationStrategy;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityManagerBuilder {
    private Connection connection;
    private String dataSource;
    private SchemaInitializationStrategy strategy;

    public Connector configureConnectionString() {
        return new Connector(this);
    }

    public StrategyConfigurer configureCreationType() {
        return new StrategyConfigurer(this);
    }

    public EntityManager build() throws SQLException, ClassNotFoundException {
        return new EntityManager(this.connection, this.dataSource, this.strategy);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDataSource() {
        return dataSource;
    }

    public EntityManagerBuilder setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public void setStrategy(SchemaInitializationStrategy strategy) {
        this.strategy = strategy;
    }

}
