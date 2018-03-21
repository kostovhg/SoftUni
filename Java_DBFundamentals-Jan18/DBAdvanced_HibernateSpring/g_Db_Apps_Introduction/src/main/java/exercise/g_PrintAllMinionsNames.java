package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

class g_PrintAllMinionsNames {

    private static final String GET_MINIONS_NAMES = "SELECT name FROM minions ORDER BY id ASC";

    public static void execute(Connection conn) {

        // Open all in try with resources to close them after finnish
        // The result cursor should be able to go in both directions
        try (PreparedStatement prstmt = conn.prepareStatement(
                GET_MINIONS_NAMES,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = prstmt.executeQuery()) {

            rs.last();
            String[] minions = new String[rs.getRow()];
            rs.beforeFirst();

            for (int i = 0; rs.next(); i++) {
                minions[i] = rs.getString(1);
            }

            String[] minionsNames = new String[minions.length];
            int first = 0;
            int last = minions.length - 1;
            for (int i = 0; first <= last ; first++, last--, i += 2) {
                minionsNames[i] = minions[first];
                if(first != last) {
                    minionsNames[i + 1] = minions[last];
                }
            }
            Arrays.asList(minionsNames).forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
