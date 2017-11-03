package p06_Animals;

public class Kitten extends Cat{

    private static final String SOUND = "Miau";

    Kitten(String[] tokens) {
        super(tokens);

    }

    @Override
    protected void setGender(String g) {
        if(!g.equals(FEMALE_GENDER)) {
            throw new IllegalArgumentException(ErrorMessageContants.INVALID_INPUT_MESSAGE);
        }
        super.gender = false;
    }

    @Override
    public String produceSound(){
        return SOUND;
    }
}
