package minionsORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static Connection connection = null;

    public static void initConnection(
            String driver,
            String username,
            String password,
            String host,
            String port,
            String dbName) throws SQLException {
        Properties connectionProps = new Properties();

        connectionProps.put("user", username);
        connectionProps.put("password", password);

        connection = DriverManager.getConnection(
                String.format(
                        "jdbc:%s://%s:%s/%s",
                        driver,
                        host,
                        port,
                        dbName),
                connectionProps);


    }

    public static Connection getConnection() {return connection;}
}
