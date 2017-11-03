package p06_Animals;

public class Dog extends Animal {

    private static final String SOUND = "BauBau";

    Dog(String[] tokens) {
        super(tokens);
    }

    @Override
    public String produceSound(){
        return SOUND;
    }
}
