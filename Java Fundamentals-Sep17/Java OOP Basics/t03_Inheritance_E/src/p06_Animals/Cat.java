package p06_Animals;

public class Cat extends Animal {

    private static final String SOUND = "MiauMiau";

    Cat(String[] tokens, boolean gender) {
        super(tokens, gender);
    }

    Cat(String[] tokens) {
        super(tokens);
    }

    @Override
    public String produceSound(){
        return SOUND;
    }
}
