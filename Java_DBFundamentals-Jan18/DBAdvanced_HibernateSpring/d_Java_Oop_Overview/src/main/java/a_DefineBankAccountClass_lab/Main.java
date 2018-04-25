package a_DefineBankAccountClass_lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<Integer, BankAccount> ACCOUNTS = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String command = reader.readLine();

        while (!command.equalsIgnoreCase("end")) {
            String[] cmdArgs = command.split("\\s+");

            switch (cmdArgs[0]){
                case "Create":
                    execCreate(cmdArgs);
                    break;
                case "Deposit":
                    execDeposit(cmdArgs);
                    break;
                case "Withdraw":
                    execWithdraw(cmdArgs);
                    break;
                case "Print":
                    execPrint(cmdArgs);
                    break;
            }
            command = reader.readLine();
        }
        /*
        System.out.printf("Account %d, balance %.2f", acc, acc.getBalance());
        */
    }

    private static void execCreate(String[] cmdArgs){
        Integer id = Integer.parseInt(cmdArgs[1]);
        if(ACCOUNTS.containsKey(id)){
            System.out.println("Account already exists");
        } else {
            BankAccount acc = new BankAccount();
            //acc.setId(id);
            ACCOUNTS.put(id, acc);
        }
    }

    private static void execDeposit(String[] cmdArgs){
        Integer id = Integer.parseInt(cmdArgs[1]);
        if(!ACCOUNTS.containsKey(id)){
            System.out.println("Account does not exist");
        } else {
            BankAccount acc = ACCOUNTS.get(id);
            try {
                acc.deposit(Double.parseDouble(cmdArgs[2]));
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }

    private static void execWithdraw(String[] cmdArgs){
        Integer id = Integer.parseInt(cmdArgs[1]);
        if(!ACCOUNTS.containsKey(id)){
            System.out.println("Account does not exist");
        } else {
            BankAccount acc = ACCOUNTS.get(id);
            try {
                acc.withdraw(Double.parseDouble(cmdArgs[2]));
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }

    private static void execPrint(String[] cmdArgs){
        Integer id = Integer.parseInt(cmdArgs[1]);
        if(!ACCOUNTS.containsKey(id)){
            System.out.println("Account does not exist");
        } else {
            BankAccount acc = ACCOUNTS.get(id);
            System.out.println(String.format("Account %s, balance %.2f", acc, acc.getBalance()));
        }
    }
}

