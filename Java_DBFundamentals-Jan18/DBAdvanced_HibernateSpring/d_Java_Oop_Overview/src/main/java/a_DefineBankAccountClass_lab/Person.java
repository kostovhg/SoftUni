package a_DefineBankAccountClass_lab;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private Integer age;
    private List<BankAccount> accounts;

    public Person(String name, int age){
        this(name, age, new ArrayList<>());
    }

    public Person(String name, int age, List<BankAccount> accounts){
        this.name = name;
        this.age = age;
        this.accounts = accounts;
    }

    public Double getBalance(){
        return this.accounts.stream().mapToDouble(BankAccount::getBalance).sum();
    }
}
