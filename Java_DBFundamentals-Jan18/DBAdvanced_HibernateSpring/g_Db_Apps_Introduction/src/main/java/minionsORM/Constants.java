package minionsORM;

public class Constants {
    // Java database URL and Driver
    // TODO: change port number from 3308 to default 3306
    public static final String URL = "jdbc:mysql://localhost:3308/MinionsDB?createDatabaseIfNotExist=true";
    //public static final String URL = "jdbc:mysql://localhost:3308/";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Credentials
    public static final String USER = "root";
    public static final String PASSWORD = "didi"; //TODO: hide the password!?

    // Database name
    public static final String DB_NAME = "MinionsDB";

    // Final SQL queries
    public static final String SQL_CREATE_QUERY =
            "CREATE TABLE IF NOT EXISTS minions(\n" +
                    "  id INT AUTO_INCREMENT PRIMARY KEY ,\n" +
                    "  name VARCHAR(25) NOT NULL,\n" +
                    "  age INT UNSIGNED NOT NULL,\n" +
                    "  town INT)";
}
