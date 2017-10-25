package p04_ConstructorsAndStaticMembers;

import java.util.ArrayList;
import java.util.List;

class Person {
    //private final static String DEFAULT_NAME = "ID";
    private static long personsCount = 0;
    private String name;
    private int age;
    private List<BankAccount> accounts;

    Person() {
        this("ID" + (++personsCount), 20);
    }

    private Person(String name, int age) {
        this(name, age, new ArrayList<>());
    }

    private Person(String name, int age, List<BankAccount> accounts) {
        this.name = name;
        this.age = age;
        this.accounts = accounts;
    }

    double getBalance() {
        return this.accounts.stream().mapToDouble(BankAccount::getBalance).sum();
    }

    void createAccount() {
        this.accounts.add(new BankAccount());
    }

    void deposit(int id, double amount) {
        if (id < 1 || id > this.accounts.size()) {
            System.out.println("Account does not exist");
        } else {
            this.accounts.get(id - 1).deposit(amount);
        }
    }

    void getInterest(int id, int years) {
        if (id < 1 || id > accounts.size()) {
            System.out.println("Account does not exist");
        } else {
            System.out.printf("%.2f%n",
                    this.accounts.get(id - 1).getInterest(years));
        }
    }
}
