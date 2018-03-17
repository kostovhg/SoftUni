package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class a_ConnectAndConnectionProperties {

    private static final String LOCALHOST_SOFT_UNI = "jdbc:mysql://localhost:3308/soft_uni?createDatabaseIfNotExist=true";
    private static final String DEFAULT_USER = "root";
    public static final String ASK_FOR_USERNAME = "Enter user default (root): ";
    public static final String ASK_FOR_PASSWORD = "Enter password default (empty): ";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(ASK_FOR_USERNAME);
        String user = reader.readLine();
        user = user.equals("") ? DEFAULT_USER : user;


        System.out.println(ASK_FOR_PASSWORD);
        String password = reader.readLine().trim();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection =
                    DriverManager.getConnection(LOCALHOST_SOFT_UNI, props);
            if (connection != null) {
                System.out.println("Connection is opened!");

                String SQL_Create = "CREATE TABLE IF NOT EXISTS admins(" +
                        "id INT AUTO_INCREMENT PRIMARY KEY ," +
                        "user VARCHAR(25)," +
                        "password VARCHAR(12))";
                String SQL_Export = "SELECT * FROM employees WHERE salary > ?";

                stmt =
                        connection.prepareStatement(SQL_Export);

                String salary = reader.readLine();
                stmt.setDouble(1, Double.parseDouble(salary));
                rs = stmt.executeQuery();

                while (rs.next()) {
                    System.out.println(
                            rs.getString("first_name") + " " +
                                    rs.getString("last_name") + " " +
                                    rs.getDouble("salary"));
                }


                stmt.executeUpdate(SQL_Create);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (stmt != null && !stmt.isClosed()) {
                    stmt.close();
                    System.out.println("Statement is closed!");
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection is closed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}