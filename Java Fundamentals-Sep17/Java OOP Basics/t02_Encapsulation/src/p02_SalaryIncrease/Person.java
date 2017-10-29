package p02_SalaryIncrease;

public class Person {
    private String firstName;
    private String lastName;
    private double salary;
    private int age;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public void increaseSalary(double bonus ){
        if(age > 30) salary += salary * bonus / 100;
        else  salary += salary * bonus / 200;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName + " gets " + salary + " leva";
    }
}
