package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.interfaces.IPrivate;

public class Private extends Soldier implements IPrivate {

    private double salary;

    public Private(
            int id, String firstName, String lastName,
            double salary) {
        super(id, firstName, lastName);
        this.setSalary(salary);
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString(){
        return String.format("%s Salary: %.2f%n",
                super.toString(),
                this.getSalary());
    }
}
