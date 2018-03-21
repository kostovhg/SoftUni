package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class h_IncreaseMinionsAge {


    private static final String UPDATE_MINIONS_AGE = "UPDATE minions SET \n" +
            "  name = CONCAT(UCASE(LEFT(name, 1)),SUBSTRING(name, 2)),\n" +
            "  age = age + 1\n" +
            "WHERE id IN ("; // the values in brackets will be added  in prepared statement
    // to prevent escaping of the string with single quotes which setStrings adds automatically

    private static final String PRINT_ALL_MINIONS = "SELECT name, age FROM minions";

    public static void execute(Connection conn, String[] minionsIds) {

        String ids = String.join(", ", minionsIds);
        try(PreparedStatement updateStmnt = conn.prepareStatement(UPDATE_MINIONS_AGE + ids + ")")){
            conn.setAutoCommit(false);
            try {
                updateStmnt.executeUpdate();
            } catch (Exception e){
                e.printStackTrace(); // only for developer information
                throw new SQLException("The provided input is not correct");
            }

            conn.commit();
            PreparedStatement prstmt = conn.prepareStatement(PRINT_ALL_MINIONS);

            ResultSet rs = prstmt.executeQuery();
            StringBuilder sb = new StringBuilder();
            while(rs.next()){
                sb.append(String.format("%s %d", rs.getString("name"), rs.getInt("age")))
                        .append(System.lineSeparator());
            }
            System.out.println(sb.toString().trim());
        } catch (SQLException e){
            try {
                conn.rollback();
                System.out.println(e.getMessage());
            } catch (SQLException e1) {
                //e1.printStackTrace();
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
