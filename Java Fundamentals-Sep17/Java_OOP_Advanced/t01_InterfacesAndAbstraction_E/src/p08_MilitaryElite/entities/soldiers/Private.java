package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.interfaces.IPrivate;

public class Private extends Soldier implements IPrivate {

    private double salary;

    public Private(String[] args) {
        super(args);
        this.setSalary(args[4]);
    }

    private void setSalary(String salaryStr) {
        this.salary = Double.parseDouble(salaryStr);
    }

    private double getSalary() {
        return this.salary;
    }

    @Override
    public String toString(){
        return String.format("%s Salary: %.2f",
                super.toString(),
                this.getSalary());
    }
}
