package p01_DefineBankAccount;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] cmdArgs = command.split("\\s+");

            testClient(cmdArgs);

            command = scanner.nextLine();
        }
    }

    private static void testClient(String[] cmdArgs) {
        String cmdType = cmdArgs[0];
        switch (cmdType){
            case "Create": execCreate(cmdArgs); break;
            case "Deposit": execDeposit(cmdArgs); break;
            case "Withdraw": execWithdraw(cmdArgs); break;
            case "Print": execPrint(cmdArgs);break;
        }
    }

    private static void execPrint(String[] cmdArgs) {
        int id = Integer.valueOf(cmdArgs[1]);
        if (!accounts.containsKey(id)) System.out.println("Account does not exist");
        else {
            BankAccount account = accounts.get(id);
            System.out.printf("Account %s, balance %.2f%n", account.toString(), account.getBalance());
        }
    }

    private static void execWithdraw(String[] cmdArgs) {
        int id = Integer.valueOf(cmdArgs[1]);
        if (!accounts.containsKey(id)) System.out.println("Account does not exist");
        else accounts.get(id).withdrow(Double.valueOf(cmdArgs[2]));
    }

    private static void execDeposit(String[] cmdArgs) {
        int id = Integer.valueOf(cmdArgs[1]);
        if (!accounts.containsKey(id)) System.out.println("Account does not exist");
        else accounts.get(id).deposit(Double.valueOf(cmdArgs[2]));
    }

    private static void execCreate(String[] cmdArgs) {
        int id = Integer.valueOf(cmdArgs[1]);
        if(accounts.containsKey(id)) System.out.println("Account already exists");
        else {
            BankAccount account = new BankAccount(id);
            accounts.put(id, account);
        }
    }
}
