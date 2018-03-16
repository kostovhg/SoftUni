package lab.d_SayHello;

public class Chinese implements Person {

    private String name;

    public Chinese(String name) {
        this.setName(name);
    }

    private void setName(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
