package p05_BorderControl;

public class Citizen extends BaseVisitor implements Visitor {

    private String name;
    private int age;

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public Citizen(String[] args){
        super(args);
        this.setName(args[0]);
        this.setAge(args[1]);
    }
}
