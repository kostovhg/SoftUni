 package a_DefineBankAccountClass_lab;

public class BankAccount {

    private static Integer ID_COUNTER = 1;

    private static Double INTEREST_RATE = 0.02;

    private Integer id;
    private Double balance;
    public BankAccount() {
        this.id = ID_COUNTER++;
        this.balance = 0.0;
    }


    /*
    public void setId(Integer id){
        this.id = id;
    }
    */
    public Double getBalance(){
        return this.balance;
    }

    public void deposit(Double amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount can not be negative!");
        }
        this.balance += amount;
    }

    public void withdraw(Double amount){
        if (this.balance - amount < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "ID" + this.id;
    }

    public static Double getInterestRate(Integer years) {
        return INTEREST_RATE;
    }

    public void setInterestRate(Double interestRate) {
        INTEREST_RATE = interestRate;
    }

}