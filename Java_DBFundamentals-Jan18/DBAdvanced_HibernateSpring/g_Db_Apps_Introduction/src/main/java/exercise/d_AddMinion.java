package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static exercise.utils.Constants.INSERT_QUERY;
import static exercise.utils.Constants.SELECT_QUERY;

class d_AddMinion {

    /**
     * The method receive a minion and villain parameters and add the minion and set it as subject of the given villain
     * If any of the given values is not in the database, they will be created and inserted.
     * The method prints corresponding messages for adding any new record to the database.
     * @param conn - the existing connection
     * @param minionParams - parameters for minion <'name' 'age' 'town'>
     * @param villainParams - parameters for villain <'name'>
     */
    public static void execute(Connection conn, String[] minionParams, String[] villainParams) {

        String minionName = "'" + minionParams[1] + "'";
        int minionAge = Integer.parseInt(minionParams[2]);
        String minionTown = "'" + minionParams[3] + "'";
        String villainName = "'" + villainParams[1] + "'";
        long townId;
        long villainId;
        long minionId;

        try (
                PreparedStatement insertMinionStmnt = conn.prepareStatement("INSERT INTO minions(name, age, town_id) VALUES (?, ?, ?)")) {
            conn.setAutoCommit(false);
            // check if there is such town (if not return 0)
            townId = getId(conn, "towns", "name", minionTown);

            /*
            Check if minion with all entered parameters already exists
            if it is so, take him and add it to the villain, else create a new minion
            (This approach is taken because it is not clear in the problem description if
            the names of the minions are unique, is the names are primary key and so on)
             */
            minionId = getId(conn,
                    "minions",
                    "name",
                    minionName +
                            " AND age = " +
                            minionAge +
                            " AND town_id = " +
                            townId);
            // if there is no such town create it and print message
            if (townId == 0
                    && insertRecord(conn, "towns", "name", minionTown)) {
                System.out.println("Town " + minionParams[3] + " was added to the database.");
                townId = getId(conn, "towns", "name", minionTown);
            }

            // Villain is independent.
            villainId = getId(conn, "villains", "name", villainName);
            if (villainId == 0
                    && insertRecord(conn, "villains", "name, evilness_factor", villainName + ", 'evil'")) {
                System.out.println("Villain " + villainParams[1] + " was added to the database.");
                villainId = getId(conn, "villains", "name", villainName);
            }

            if (minionId == 0) {
                // Prepare the statement to insert new minion
                insertMinionStmnt.setString(1, minionParams[1]);
                insertMinionStmnt.setInt(2, minionAge);
                insertMinionStmnt.setLong(3, townId);
                insertMinionStmnt.execute();

                conn.commit();
                // get the last id as the minion is newly added
                minionId = getId(conn, "minions", "1", " 1 ORDER BY id DESC LIMIT 1");
            }

            // Connecting the minion to the villain
            if(insertRecord(conn, "minions_villains", "minion_id, villain_id", minionId + ", " + villainId)){
                System.out.println("Successfully added " + minionParams[1] + " to be minion of " + villainParams[1]);
            } else {
                System.out.println("Minion " + minionParams[1] + " is already added as minion of " + villainParams[1]);
            }
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * Common method for getting a record id if exist.
     * @param conn - reference to the opened connection
     * @param tableName - table to be searched for record
     * @param searchParam - name of the column where the criteria will be checked
     * @param searchValue - criteria
     * @return long as ID of object or 0 if there is no record that meets the given criteria
     */
    private static long getId(Connection conn, String tableName, String searchParam, String searchValue) {
        try (PreparedStatement getIdStmnt = conn.prepareStatement(
                String.format(SELECT_QUERY, "id", tableName, searchParam, searchValue))) {

            ResultSet rs = getIdStmnt.executeQuery();
            conn.commit();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return 0;
    }

    /**
     * Inserting record into the database
     * @param conn - reference to opened connection to the database
     * @param tableName - table where the record will be inserted
     * @param params - columns/fields that will be recorded
     * @param values - values that will be inserted
     * @return true if the record was inserted into the database, else return false
     */
    private static boolean insertRecord(Connection conn, String tableName, String params, String values) {
        try (PreparedStatement insertStmt = conn.prepareStatement(
                String.format(INSERT_QUERY, tableName, params, values))) {

            insertStmt.execute();
            return true;
//        } catch (MySQLIntegrityConstraintViolationException msqlicve){
//            return false;
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return false;
    }
}