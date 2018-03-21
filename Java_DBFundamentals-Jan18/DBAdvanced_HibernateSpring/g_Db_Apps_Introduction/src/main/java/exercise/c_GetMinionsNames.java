package exercise;

import java.sql.*;

class c_GetMinionsNames {

    /**
     * Method that returns to the console a list of minions for villain with given id
     * @param conn - the existing connection
     * @param id - villains id
     */
    public static void execute(Connection conn, long id) {

        try (
                PreparedStatement minionsStmt = conn.prepareStatement(GET_MINIONS_NAMES);
                PreparedStatement villainStmt = conn.prepareStatement(GET_VILLAIN_NAME)
        ) {

            minionsStmt.setString(1, String.valueOf(id));
            villainStmt.setString(1, String.valueOf(id));

            ResultSet vrs = villainStmt.executeQuery();
            if (!vrs.next()){
                System.out.print("\nNo villain with ID " + id + " exists in the database.\n");
                return;
            }
            System.out.println("Villain: " +  vrs.getString(1));

            ResultSet rs = minionsStmt.executeQuery();
            int rows = 1;
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(String.format(
                        "%d. %s %s",
                        rows,
                        rs.getString(1),
                        rs.getString(2)))
                        .append(System.lineSeparator());
                rows++;
            }
            System.out.println(sb.toString().trim());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    QUERIES for prepared statements
     */
    private static final String GET_VILLAIN_NAME =
            "SELECT name FROM villains WHERE id = ?";

    private static final String GET_MINIONS_NAMES =
            "SELECT m.name, m.age\n" +
                    "FROM minions AS m\n" +
                    "  JOIN minions_villains AS mv\n" +
                    "ON m.id = mv.minion_id\n" +
                    "WHERE mv.villain_id = ?";

}
