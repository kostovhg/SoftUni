package p06_Animals;

public class Frog extends Animal {

    private static final String SOUND = "Frogggg";

    Frog(String[] tokens) {
        super(tokens);
    }

    @Override
    public String produceSound() {
        return SOUND;
    }
}
