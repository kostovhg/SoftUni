package entities;

public class User {

    private String name;
    private Integer age;

    public User(String name, String age) {
        this.name = name;
        this.setAge(age);
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = Integer.valueOf(age);
    }
}
