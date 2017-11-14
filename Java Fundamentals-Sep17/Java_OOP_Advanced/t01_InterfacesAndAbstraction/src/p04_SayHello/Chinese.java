package p04_SayHello;

public class Chinese extends BasePerson implements Person {

    Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
