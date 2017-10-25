package p01_DefineBankAccount;

public class BankAccount {
    private int id;
    private double balance;
    private final static double DEFAULT_INTEREST = 0.02;
    private static double rate = DEFAULT_INTEREST;
    private static int bankAccountCount;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    void deposit(double amount){
        if(amount > 0) this.balance += amount;
        else System.out.println("Insufficient amount");
    }

    void withdrow(double amount){
        if(balance - amount < 0) System.out.println("Insufficient balance");
        else if(amount > 0 ) this.balance -= amount;
        else System.out.println("Insufficient amount");
    }

    public static void setInterestRate(double rate) {
        BankAccount.rate = rate;
    }

    public double getInterest(int years){
        return this.balance * rate * years;
    }

    @Override
    public String toString(){
        return "ID" + this.id;
    }

    BankAccount(int num){
        this.id = ++bankAccountCount;
        this.balance = 0;
    }

}
