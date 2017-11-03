package p06_Animals;

public class Tomcat extends Cat {

    Tomcat(String[] tokens) {
        super(tokens);
    }

    @Override
    protected void setGender(String g) {
        if(!g.equals(MALE_GENDER)){
            throw new IllegalArgumentException(ErrorMessageContants.INVALID_INPUT_MESSAGE);
        }
        super.gender = true;
    }

    @Override
    public String produceSound(){
        return "Give me one million b***h";
    }
}
