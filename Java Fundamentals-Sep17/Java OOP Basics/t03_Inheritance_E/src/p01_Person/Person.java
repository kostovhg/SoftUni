package p01_Person;

public class Person {
    private String name;
    private int age;

    public Person(String personName, int personAge) {
        this.setName(personName);
        this.setAge(personAge);
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) throws IllegalArgumentException {
        if (name.length() < 3) {
            throw new IllegalArgumentException(" Name's length should not be less than 3 symbols!");
        }
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    protected void setAge(int age) throws IllegalArgumentException {
        if (age < 0 ) {
            throw new IllegalArgumentException("Age must be positive!");
        }
        this.age = age;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s, Age: %d",
                this.getName(),
                this.getAge()));
        return sb.toString();
    }
}
