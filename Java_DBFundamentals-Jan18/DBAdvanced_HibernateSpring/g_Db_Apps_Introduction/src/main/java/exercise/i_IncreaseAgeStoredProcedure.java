package exercise;

import java.sql.*;

import static exercise.utils.Constants.DB_NAME;

class i_IncreaseAgeStoredProcedure {

    private static final String STORED_PROCEDURE_NAME = "usp_get_older";
    private static final String CHECK_IF_PROCEDURE_EXISTS = "SELECT SPECIFIC_NAME\n" +"" +
            "FROM information_schema.ROUTINES\n" +
            "WHERE ROUTINE_SCHEMA = '" + DB_NAME + "'\n" +
            " AND ROUTINE_NAME = '" + STORED_PROCEDURE_NAME + "'\n";
    private static final String CREATE_PROCEDURE = "CREATE PROCEDURE " + STORED_PROCEDURE_NAME +
            "(_id INT)\n" +
            "  BEGIN\n" +
            "    UPDATE minions AS m SET m.age = m.age +1 WHERE m.id = _id;\n" +
            "    SELECT name, age FROM minions WHERE id = _id;\n" +
            "  END";


    public static void execute(Connection conn, long theId) {

        try (PreparedStatement checkIfProcedureExistStmnt =
                     conn.prepareStatement(CHECK_IF_PROCEDURE_EXISTS)){

            conn.setAutoCommit(false);

            ResultSet rs = checkIfProcedureExistStmnt.executeQuery();
            if (!rs.next()) {
                try (PreparedStatement createProcedureStmnt = conn.prepareStatement(CREATE_PROCEDURE)) {
                    createProcedureStmnt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            rs.close();

            try(CallableStatement cStmt = conn.prepareCall("CALL " + STORED_PROCEDURE_NAME + " (?)")){
                cStmt.setLong(1, theId);
                ResultSet res = cStmt.executeQuery();
                if(res.next()){
                    System.out.printf("%s %d%n", res.getString(1), res.getInt(2) );
                } else {
                    System.out.println("There is no minion with id = " + theId);
                }
                res.close();
            }

            conn.commit();
        } catch (SQLException e){
            try {
                conn.rollback();
                System.out.println("Transaction was rolled back.");
                e.printStackTrace();
            } catch (SQLException e1) {
                System.out.println("Transaction can not be rolled back");
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
