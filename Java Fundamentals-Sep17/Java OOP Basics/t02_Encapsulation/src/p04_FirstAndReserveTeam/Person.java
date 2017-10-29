package p04_FirstAndReserveTeam;

public class Person {

    private String firstName;
    private String lastName;
    private double salary;
    private int age;

    public Person(String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
    }

    public void setFirstName(String firstName) {
        if(firstName.length() < 3){
            throw new IllegalArgumentException("First name cannot be less than 3 symbols");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.length() < 3){
            throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
        }
        this.lastName = lastName;
    }

    public void setSalary(double salary) {
        if(salary < 460){
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
        this.salary = salary;
    }

    public void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    public int getAge(){
        return age;
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

/*
Age cannot be zero or negative integer
First name cannot be less than 3 symbols
Last name cannot be less than 3 symbols
Salary cannot be less than 460 leva

 */