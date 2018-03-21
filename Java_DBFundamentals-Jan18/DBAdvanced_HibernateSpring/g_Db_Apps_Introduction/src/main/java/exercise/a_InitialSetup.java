package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static exercise.utils.Constants.DB_NAME;

class a_InitialSetup {

    /**
     * The method create tables and fill them with some data
     * @param conn - the existing connection
     */

    public static void execute(Connection conn) {

        PreparedStatement pstmt = null;

        try {
            conn.setAutoCommit(false);
            // next line is not necessary, because URL parameters
            pstmt = conn.prepareStatement("USE ?");
            pstmt.setString(1, DB_NAME);

            // create tables statements
            pstmt.addBatch(CREATE_TOWNS_TABLE);
            pstmt.addBatch(CREATE_MINIONS_TABLE);
            pstmt.addBatch(CREATE_VILLAINS_TABLE);
            pstmt.addBatch(CREATE_MINIONS_VILLAINS_TABLE);

            // statements insert 5 records in each table
            // TODO: insert check for count of records in each table before insert
            // remove from batch;
            pstmt.addBatch(INSERT_INITIAL_RECORDS_IN_TOWN);
            pstmt.addBatch(INSERT_INITIAL_RECORDS_IN_MINIONS);
            pstmt.addBatch(INSERT_INITIAL_RECORDS_IN_VILLAINS);
            pstmt.addBatch(INSERT_INITIAL_RECORDS_IN_MINIONS_VILLAINS);

            pstmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
           // e.printStackTrace();
            try {
                conn.rollback();
                System.err.print("Transaction is being rolled back");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * SQL queries for each of the operations. (Could be automated with methods?)
     */
    private static final String CREATE_TOWNS_TABLE =
            "CREATE TABLE IF NOT EXISTS towns(\n"+
                    "  id INT AUTO_INCREMENT PRIMARY KEY ,\n"+
                    "  name VARCHAR(30) NOT NULL,\n"+
                    "  information VARCHAR(250)\n"+
                    ")";
    private static final String CREATE_MINIONS_TABLE =
            "CREATE TABLE IF NOT EXISTS minions(\n"+
                    "  id INT AUTO_INCREMENT PRIMARY KEY ,\n"+
                    "  name VARCHAR(30) NOT NULL,\n"+
                    "  age INT UNSIGNED NOT NULL,\n"+
                    "  town_id INT NOT NULL,\n"+
                    "  CONSTRAINT fk_minion_town\n"+
                    "  FOREIGN KEY (town_id) REFERENCES towns(id)\n"+
                    ")";
    private static final String CREATE_VILLAINS_TABLE =
            "CREATE TABLE IF NOT EXISTS villains(\n"+
                    "  id INT AUTO_INCREMENT PRIMARY KEY,\n"+
                    "  name VARCHAR(30) NOT NULL,\n"+
                    "  evilness_factor ENUM('good', 'bad', 'evil', 'super evil') NOT NULL\n"+
                    ")";
    private static final String CREATE_MINIONS_VILLAINS_TABLE =
            "CREATE TABLE IF NOT EXISTS minions_villains(\n"+
                    "  minion_id int not null,\n"+
                    "  villain_id int not null,\n"+
                    "  CONSTRAINT fk_minions_villains_minion\n" +
                    "  FOREIGN KEY (minion_id) REFERENCES minions(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT fk_minions_villains_villain\n" +
                    "  FOREIGN KEY (villain_id) REFERENCES villains(id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "  CONSTRAINT pk_minions_villains\n"+
                    "  PRIMARY KEY (minion_id, villain_id)\n" +
                    ")";

    // TODO: Fix insertion in non empty tables!?
    // Count records from tables and if there is none, then insert!
    private static final String INSERT_INITIAL_RECORDS_IN_TOWN =
            "INSERT INTO towns (name, information)\n" +
                    "VALUES\n" +
                    "  ('Sofia', 'Bulgaria'), ('Berlin', 'Germany'), ('Plovdiv', 'Bulgaria'), ('Varna', 'Bulgaria'), ('Viena', NULL)";

    private static final String INSERT_INITIAL_RECORDS_IN_MINIONS =
                    "INSERT INTO minions (name, age, town_id)\n" +
                    "VALUES\n" +
                    "  ('Bob', 13, 2), ('Kevin', 14, 1), ('Steward', 19, 3), ('Simon', 22, 1), ('Jimmy', 20, 2), ('Vicky', 10, 4),\n" +
                    "  ('Becky', 14, 2), ('Jully', 14, 1), ('Vicky Jackson', 10, 3), ('Bob Steward', 11, 1), ('Jimmy Bobson', 20, 2), ('Kevin Finlly', 10, 4)";

    private static final String INSERT_INITIAL_RECORDS_IN_VILLAINS =
            "INSERT INTO villains (name, evilness_factor)\n" +
                    "VALUES ('Gru', 'evil'), ('Victor', 'evil'), ('Garry', 'evil'), ('Gosho', 'evil'), ('Mara', 'evil'),\n" +
                    "  ('Itso', 'evil')";

    private static final String INSERT_INITIAL_RECORDS_IN_MINIONS_VILLAINS =
            "INSERT INTO minions_villains (minion_id, villain_id)\n" +
                    "VALUES\n" +
                    "  (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),\n" +
                    "  (2, 1), (2, 2), (2, 3), (2, 5),\n" +
                    "  (4, 1), (4, 5), (4, 3), (4, 2), (4, 4),\n" +
                    "  (3, 3), (3, 4), (3, 1),\n" +
                    "  (6, 1), (6, 2), (6, 4), (6, 5),\n" +
                    "  (7, 1), (7, 3), (7, 4), (7, 5),\n" +
                    "  (8, 1),\n" +
                    "  (9, 3), (9, 5), (9, 1),\n" +
                    "  (1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6), (7, 6), (8, 6), (9, 6), (10, 6), (11, 6)";
}
