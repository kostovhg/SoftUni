package exercise.utils;

public class Constants {

    // Java database URL elements and Driver
    private static final String DRIVER_NAME = "jdbc";
    static final String HOST_NAME = "localhost";
    public static final String DB_NAME = "MinionsDB";
    static final String DEFAULT_PORT = "3306";

    // Credentials
    static final String DEFAULT_USER = "root";
     static final String DEFAULT_PASSWORD = "";

    // Prepare parameters for URL
    private static final String[] URL_PARAMETERS = {
            "createDatabaseIfNotExist=true",
            "allowMultiQueries=true",
            "useSSL=false"
    };

    private static final String ADDITIONAL_PARAMETERS_TO_URL = String.join("&", URL_PARAMETERS);
    // Join parameters with delimiter "&"

    private static final String URL_FORMAT = "%s:mysql://%s:%s/%s?%s";
    static final String URL = String.format(URL_FORMAT, DRIVER_NAME, HOST_NAME, DEFAULT_PORT, DB_NAME, ADDITIONAL_PARAMETERS_TO_URL);

    public static final String INSERT_QUERY = "INSERT INTO %s(%s) VALUES(%s)";
    // --Commented out by Inspection (3/20/2018 15:31):public static final String UPDATE_QUERY = "UPDATE ? SET ? WHERE ?";
    // --Commented out by Inspection (3/20/2018 15:31):public static final String DELETE_QUERY = "DELETE FROM ? WHERE ?";
    public static final String SELECT_QUERY = "SELECT %s FROM %s WHERE %s = %s";

}
