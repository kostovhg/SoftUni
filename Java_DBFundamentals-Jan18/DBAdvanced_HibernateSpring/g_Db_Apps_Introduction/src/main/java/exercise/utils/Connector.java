package exercise.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static exercise.utils.Constants.*;

public class Connector {
    private static Connection connection = null;

    public static Connection initConnection(String port, String user, String password) throws SQLException {

        Properties connectionProps = new Properties();

        // Check for any value from method parameters different from "" to replace them with default set in Constants class
        connectionProps.put("user", user.equals("") ? DEFAULT_USER : user);
        connectionProps.put("password", password.equals("") ? DEFAULT_PASSWORD : password);

        connection = DriverManager.getConnection(
                port.equals("") ? URL : URL.replace("3306", port),
                connectionProps);
        System.out.println(String.format("Connected successfully to %s at %s:%s", DB_NAME, HOST_NAME, port.equals("")? DEFAULT_PORT : port));
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }
}
