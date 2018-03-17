package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class z_LectureDemos {

    private static final String LOCALHOST_SOFT_UNI = "jdbc:mysql://localhost:3308/soft_uni?createDatabaseIfNotExist=true&allowMultiQueries=true";
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
            connection = DriverManager.getConnection(LOCALHOST_SOFT_UNI, props);

            if (login(connection, "Gerry", "12345")) {
                System.out.println("Gerry logged in!");
            } else {
                System.out.println("No admin with this name!");
            }

            // Needs of allowMultiQueries=true property to url to work
            // creating user evil Jack with password 0 in admins table...
//            String injection = "'; INSERT INTO admins(user, password) VALUES('evil Jack', '0') #";
//            if(login(connection, injection, "0")){
//                System.out.println("evil Jack created!");
//            } else {
//                System.out.println("No admin with this name!");
//            }
            String injection_two = "'; INSERT INTO admins(user, password) VALUES('stupid Jack', '0') #";
            if (secureLogin(connection, injection_two, "5")) {
                System.out.println("Stupid Jack created!");
            } else {
                System.out.println("Wrong user!");
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

    public static boolean login(Connection conn, String user, String passphrase) {
        boolean result = false;
        String SQLInjection = "SELECT * FROM admins WHERE user='" + user +
                "' AND password = '" + passphrase + "'";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQLInjection);

            result = rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean secureLogin(Connection conn, String user, String passphrase) {
        boolean result = false;
        String SQLInjection = "SELECT * FROM admins WHERE user=? AND password=?";
        try (PreparedStatement stmt = conn.prepareStatement(SQLInjection)) {
            stmt.setString(1, user);
            stmt.setString(2, passphrase);
            ResultSet rs = stmt.executeQuery();

            result = rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}