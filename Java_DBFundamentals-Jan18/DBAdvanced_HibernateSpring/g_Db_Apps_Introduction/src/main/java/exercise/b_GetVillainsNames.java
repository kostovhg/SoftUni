package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class b_GetVillainsNames {

    /**
     * The method returns to the console all villains and their minions where count of minions > moreThan
     * @param conn the existing connection
     * @param moreThan int
     */
    public static void execute(Connection conn, int moreThan) {

        try (PreparedStatement pstmt = conn.prepareStatement(GET_VILLAINS_NAMES)){
            pstmt.setString(1, String.valueOf(moreThan));

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.printf("%s %s%n", rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    QUERY for prepared statement
     */

    private static final String GET_VILLAINS_NAMES =
            "SELECT\n" +
                    "  v.name,\n" +
                    "  COUNT(mv.minion_id) AS `minions_count`\n" +
                    "FROM villains AS v\n" +
                    "JOIN minions_villains AS mv\n" +
                    "ON v.id = mv.villain_id\n" +
                    "GROUP BY v.id\n" +
                    "HAVING `minions_count` > ?\n" +
                    "ORDER BY `minions_count` DESC;";

}
