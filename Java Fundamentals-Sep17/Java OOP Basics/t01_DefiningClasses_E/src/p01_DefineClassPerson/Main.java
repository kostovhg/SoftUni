package p01_DefineClassPerson;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Class person = Person.class;

        Field[] fields = person.getDeclaredFields();
        System.out.println(fields.length);

        Person pesho = new Person();
        pesho.setName("Pesho");
        pesho.setAge(20);
        Person gosho = new Person("Gosho", 18);
        Person stamat = new Person("Stamat", 43);
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
