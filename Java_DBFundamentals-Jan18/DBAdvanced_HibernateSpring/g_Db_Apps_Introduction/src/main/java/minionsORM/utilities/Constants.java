package minionsORM.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {

    // Credentials
    public static final String DEFAULT_USER = "root";
    public static final String DEFAULT_PASSWORD = "";
    public static final String DEFAULT_PORT = "3306";
    // Database name
    public static final String DB_NAME = "MinionsDB";


    public static final Map<String, String> CONN_PROPERTIES;
    static {
        CONN_PROPERTIES = new LinkedHashMap<>();
        CONN_PROPERTIES.put("port", DEFAULT_PORT);
        CONN_PROPERTIES.put("username", DEFAULT_USER);
        CONN_PROPERTIES.put("password", DEFAULT_PASSWORD);
    }

    public static final Map<String, String> CONN_OPTIONS;
    static {
        CONN_OPTIONS = new LinkedHashMap<>();
        CONN_OPTIONS.put("createDatabaseIfNotExist", "true");
        CONN_OPTIONS.put("allowMultiQueries", "true");
        CONN_OPTIONS.put("useSSL", "false");
    }
    public static final String URL_FORMAT = "%s:%s://%s:%s/" + DB_NAME + "?%s";


    // Final SQL queries
    public static final String SQL_CREATE_QUERY =
            "CREATE TABLE IF NOT EXISTS minions(\n" +
                    "  id INT AUTO_INCREMENT PRIMARY KEY ,\n" +
                    "  name VARCHAR(25) NOT NULL,\n" +
                    "  age INT UNSIGNED NOT NULL,\n" +
                    "  town INT)";
}
