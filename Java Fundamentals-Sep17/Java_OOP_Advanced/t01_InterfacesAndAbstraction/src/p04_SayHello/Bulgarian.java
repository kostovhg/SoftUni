package p04_SayHello;

public class Bulgarian extends BasePerson implements Person{

    Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
