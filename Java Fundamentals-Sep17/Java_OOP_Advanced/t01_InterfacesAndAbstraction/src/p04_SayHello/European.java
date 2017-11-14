package p04_SayHello;

public class European extends BasePerson implements Person {

    European(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
