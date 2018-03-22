package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class f_RemoveVillain {

    private static final String DB_NAME = "MinionsDB";
    private static final String REF_TABLE = "villains";
    private static final String REF_TABLE_PK = "id";

    // Query for check the name of foreign key constraint that connect minions to villains
    private static final String GET_CONSTRAINT_NAME =
            "SELECT\n" +
                    "  CONSTRAINT_NAME,\n" +
                    "  COLUMN_NAME\n" +
                    "FROM information_schema.KEY_COLUMN_USAGE\n" +
                    "WHERE TABLE_SCHEMA = ? AND REFERENCED_TABLE_NAME = ? AND REFERENCED_COLUMN_NAME = ?";

    // Query for dropping the foreign key constraint - constraint name is added as pure string
    private static final String DROP_CONSTRAINT =
            "ALTER TABLE minions_villains DROP FOREIGN KEY ";

    // Query for take the count of minions and the name of the villain with given id.
    private static final String COUNT_VILLAIN_MINIONS =
            "SELECT\n" +
                    "  v.name,\n" +
                    "  COUNT(minion_id) AS `minions_count`\n" +
                    "FROM minions_villains as mv\n" +
                    "  JOIN villains v ON mv.villain_id = v.id\n" +
                    "GROUP BY villain_id\n" +
                    "HAVING villain_id = ?";

    // Query for deleting the villain
    private static final String DELETE_VILLAIN =
            "DELETE FROM villains WHERE id = ?";

    public static void execute(Connection conn, long villainId) {

        String constraint_name = "";
        String constraint_column = "";
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement prstmt = conn.prepareStatement(GET_CONSTRAINT_NAME)) {
                prstmt.setString(1, DB_NAME);
                prstmt.setString(2, REF_TABLE);
                prstmt.setString(3, REF_TABLE_PK);

                ResultSet rs = prstmt.executeQuery();
                if (rs.next()) {
                    constraint_name = rs.getString(1);
                    constraint_column = rs.getString(2);
                } else {

                }
            } catch (SQLException e) {
                throw new SQLException();
            }

            // Drop constraint if exists, create new - for any case to ensure that ON DELETE CASCADE;
            if (!constraint_name.equals("")) {
                try (PreparedStatement prstmt = conn.prepareStatement(DROP_CONSTRAINT + constraint_name)) {

                    prstmt.execute();

                } catch (SQLException ex) {
                    throw new SQLException();
                }
            } else {
                //constraint_name = "fk_minions_villains_villain";
                constraint_column = "villain_id";
            }

            // create new - for any case to ensure that ON DELETE CASCADE in table with many_to_many connection
            // The query uses many previously set variables and it si more convenient to be assembled
            // right before to be used.
            String createRef = "ALTER TABLE minions_villains\n" +
                    "  ADD CONSTRAINT fk_minions_villains_villain\n" +
                    "FOREIGN KEY (`" + constraint_column + "`) REFERENCES " +
                    REF_TABLE + "(" + REF_TABLE_PK + ") ON DELETE CASCADE ON UPDATE CASCADE";
            try (PreparedStatement prstmt = conn.prepareStatement(createRef)) {

                prstmt.execute();

            } catch (SQLException e) {
                throw new SQLException();
            }

            // Get villain minions and name
            try (
                    PreparedStatement countStmt =
                            conn.prepareStatement(COUNT_VILLAIN_MINIONS);
                    PreparedStatement removeStmt =
                            conn.prepareStatement(DELETE_VILLAIN)) {

                countStmt.setLong(1, villainId);
                removeStmt.setLong(1, villainId);

                int minionsCount;
                String villainName = "";
                ResultSet rs = countStmt.executeQuery();

                // If there is a villain with that id
                if (rs.next()) {
                    minionsCount = rs.getInt("minions_count");
                    villainName = rs.getString("name");

                    removeStmt.execute();
                    System.out.println(villainName + " was deleted");
                    System.out.println(minionsCount + " minions released");
                } else {
                    System.out.println("No such villain was found");
                }
            } catch (SQLException e) {
                throw new SQLException();
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Changes are rolled back");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
