package exercise;

import exercise.utils.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    // ??? Keeping opened connection as long as the program is running ???
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {


        System.out.println("Connecting to server.");

        System.out.print("Enter port number (default is \"3306\"): ");
        String port = reader.readLine();
        System.out.print("Enter user (default is \"root\"): ");
        String user = reader.readLine();
        System.out.print("Enter password (default is empty): ");
        String password = reader.readLine();

        try (Connection conn = Connector.initConnection(port, user, password)) {
            chooseAction(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void chooseAction(Connection conn) throws IOException, SQLException {
        int choice = 1;
        while (choice != 0) {
            printMenu();
            choice = readUserChoice(reader.readLine());
            switch (choice) {
                case 1:
                    // 1. Initial Setup is in a_InitialSetup.
                    // Connection is done by Connector and Constants class
                    try {
                        a_InitialSetup.execute(conn);
                        System.out.println("--- Result ---\nDatabase was created.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // 2. Get Villains' Names with more than
                    System.out.println("--- Result ---\n");
                    b_GetVillainsNames.execute(conn, 3);
                    break;
                case 3:
                    // 3. Get Minion Names
                    System.out.println("Enter villain id: ");
                    long id = Integer.parseInt(reader.readLine());
                    System.out.println("--- Result ---\n");
                    c_GetMinionsNames.execute(conn, id);
                    break;
                case 4:
                    // 4. Add minion
                    System.out.println("Enter data for minion and its villain in format\n Minion: <name> <age> <town>\nVillain: <name>");
                    String[] minionParams = reader.readLine().split("\\s+");
                    String[] villainParams = reader.readLine().split("\\s+");
                    System.out.println("--- Result ---\n");
                    d_AddMinion.execute(conn, minionParams, villainParams);
                    break;
                case 5:
                    System.out.println("Enter name of country: ");
                    String country = reader.readLine();
                    System.out.println("--- Result ---\n");
                    e_ChangeTownNamesCasing.execute(conn, country);
                    break;
                case 6:
                    System.out.print("Enter the id of villain to be removed: ");
                    long villainId = 0;
                    try {
                        villainId = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException nfe) {
                        System.out.println("Incorrect number format. Returning to the menu.");
                    }
                    System.out.println("--- Result ---\n");
                    f_RemoveVillain.execute(conn, villainId);
                    break;
                case 7:
                    System.out.println("--- Result ---\n");
                    g_PrintAllMinionsNames.execute(conn);
                    break;
                case 8:
                    System.out.println("Enter minions id's separated by space:");
                    String[] minionsIds = reader.readLine().split("\\s+");
                    System.out.println("--- Result ---\n");
                    h_IncreaseMinionsAge.execute(conn, minionsIds);
                    break;
                case 9:
                    System.out.println("What is the id of the minion you like to get older with 1 year: ");
                    long theId;
                    try {
                        theId = Long.parseLong(reader.readLine());
                        System.out.println("--- Result ---\n");
                        i_IncreaseAgeStoredProcedure.execute(conn, theId);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Sorry but that was not a number.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Not yet implemented.");
            }
            System.out.print("\nContinue (0 for exit):");
            if (choice == 0 || reader.readLine().equals("0")) break;
        }
        conn.close();
        System.out.println("End program.");
    }

    private static int readUserChoice(String choice) throws IOException {

        while (!choice.matches("[0-9]")) {
            System.out.print("\nIncorrect choice. Select from 0 - 9");
            choice = reader.readLine();
        }
        return Integer.parseInt(choice);
    }

    private static void printMenu() {
        System.out.print("\nChoose one of following :\n" +
                " 1. Initial setup - create tables and inserting values (it should be done first)\n" +
                " 2. Get Villains' Name - get all villains with more than 3 minions\n" +
                " 3. Get Minion Names - get minions of villain with requested id\n" +
                " 4. Add Minion - add minion\n" +
                " 5. Change Town Names Casing - change to uppercase all towns with given country in their information\n" +
                " 6. Remove Villain - remove villain and free its minions\n" +
                " 7. Print All Minions Names - print all minions in pattern first, last, first -1, last - 1 ...\n" +
                " 8. Increase Minions Age - increase age by 1 and first symbol from the name to upper case and print them\n" +
                " 9. Increase Age Stored Procedure - use procedure to increase age by 1 of minion with given id (create procedure if not exists)\n" +
                " 0. End program\n");
    }
}