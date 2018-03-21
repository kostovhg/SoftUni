package exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class e_ChangeTownNamesCasing {

    /**
     * Change the names of towns from given country to uppercase and write the result to the console
     *
     * @param conn - the existing connection
     * @param country - a country where the town is, which is in towns.information column
     */
    public static void execute(Connection conn, String country) {
        String GET_CHANGED_TOWNS = "SELECT name FROM towns WHERE information LIKE (?)";
        String CHANGE_TOWN_NAMES_STMT = "UPDATE towns SET name = UPPER(name) WHERE information LIKE (?)";
        try (
                PreparedStatement updatePstmt = conn.prepareStatement(CHANGE_TOWN_NAMES_STMT);
                PreparedStatement selectPstmt = conn.prepareStatement(GET_CHANGED_TOWNS)) {
            conn.setAutoCommit(false);

            // TODO: if the name of the town was already uppercase, exclude it from the count of affected towns
            // First read the towns that are not uppercase and create a list from them, and after that use it
            // in the update clause as "IN (<list of towns>) so the commit will be at the end after update clause
            updatePstmt.setString(1, "%" + country + "%");
            updatePstmt.executeUpdate();
            conn.commit();

            selectPstmt.setString(1, "%" + country + "%");
            ResultSet rs = selectPstmt.executeQuery();
            List<String> changedNames = new ArrayList<>();
            while (rs.next()) {
                changedNames.add(rs.getString(1));
            }
            if (changedNames.size() > 0) {
                System.out.println(String.format("%d town names were affected.", changedNames.size()));
                System.out.println(String.format("[%s]", String.join(", ", changedNames)));
            } else {
                System.out.println("No town names were affected");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                System.err.print("Transaction is being rolled back");
            } catch (SQLException excep) {
                excep.printStackTrace();
            }
        }
    }
}
