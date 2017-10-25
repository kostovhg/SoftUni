package p04_ConstructorsAndStaticMembers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Person client;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        client = new Person();

        String command;
        while (!"End".equals(command = reader.readLine())) {
            String[] commArgs = command.split("\\s+");

            int id;

            switch (commArgs[0]) {
                case "Create":
                    client.createAccount();
                    break;
                case "Deposit":
                    id = Integer.valueOf(commArgs[1]);
                    double amount = Double.valueOf(commArgs[2]);
                    client.deposit(id, amount);
                    break;
                case "SetInterest":
                    double interest = Double.valueOf(commArgs[1]);
                    BankAccount.setInterest(interest);
                    break;
                case "GetInterest":
                    id = Integer.valueOf(commArgs[1]);
                    int years = Integer.valueOf(commArgs[2]);
                    client.getInterest(id, years);
                    break;
            }
        }
    }
}
