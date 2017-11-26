package f_StrategyPattern;

public class Person {

    private String name;
    private int age;

    public Person(String... data){
        this.name = data[0];
        this.age = Integer.parseInt(data[1]);
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return String.format("%s %d", this.name, this.age);
    }
}
