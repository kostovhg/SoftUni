package p04_ConstructorsAndStaticMembers;

public class BankAccount {

    private final static double DEFAULT_INTEREST = 0.02;

    private static double rate = DEFAULT_INTEREST;
    private static int bankAccountCount = 0;

    private int id;
    private double balance;

    BankAccount() {
        this.id = ++bankAccountCount;
        System.out.printf("Account ID%d created%n", id);
    }

    static void setInterest(double interest) {
        rate = interest;
    }

    void deposit(double amount) {
        if (amount <= 0d) {
            System.out.println("Insufficient amount");
        } else {
            this.balance += amount;
            System.out.printf(
                    "Deposited %s to ID%d%n",
                    (amount == (int) amount) ?
                            String.format("%d", (int) amount) :
                            String.format("%f", amount),
                    id);
        }
    }

    double getInterest(int years) {
        return this.balance * rate * years;
    }

    double getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return "ID" + this.id;
    }
}
