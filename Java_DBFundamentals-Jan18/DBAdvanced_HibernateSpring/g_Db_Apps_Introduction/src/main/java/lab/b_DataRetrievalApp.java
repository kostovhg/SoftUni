package lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class b_DataRetrievalApp {

    public static final String URL = "jdbc:mysql://localhost:3308/diablo";
    public static final String USER = "root";
    public static final String PSW = "******";
    public static final String SQLStatement =
            "SELECT\n" +
                    "  u.user_name,\n" +
                    "  u.first_name,\n" +
                    "  u.last_name,\n" +
                    "  COUNT(DISTINCT ug.game_id) AS 'games_played'\n" +
                    "FROM users AS u\n" +
                    "  JOIN users_games AS ug\n" +
                    "    ON u.id = ug.user_id\n" +
                    "GROUP BY user_name\n" +
                    "HAVING user_name = ?";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try (Connection conn = DriverManager.getConnection(URL,USER,PSW)){
            PreparedStatement prstmt = conn.prepareStatement(SQLStatement);
            String user_name = reader.readLine();
            prstmt.setString(1, user_name);
            ResultSet rs = null;

            rs = prstmt.executeQuery();
            if(rs == null) throw new SQLException("No result set");
            rs.first();
                String result = String.format("User: %s%n%s %s has played %d games",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4));
                System.out.println(result);

        } catch (SQLException sqle){
            System.out.println("No such user exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
