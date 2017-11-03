package p06_Animals;

public class Animal {
    static final String MALE_GENDER = "Male";
    static final String FEMALE_GENDER = "Female";
    private static final String SOUND = "Not implemented!";
    private String name;
    private int age;
    boolean gender;

    Animal(String[] tokens) {
        this.setName(tokens[0]);
        this.setAge(tokens[1]);
        this.setGender(tokens[2]);
    }

    Animal(String[] tokens, boolean g) {
        this.setName(tokens[0]);
        this.setAge(tokens[1]);
        this.gender = g;
    }

    private String getName() {
        return name;
    }

    private void setName(String n) {
        if (n.isEmpty() || n.trim().length() < 1) {
            throw new IllegalArgumentException(ErrorMessageContants.INVALID_INPUT_MESSAGE);
        }
        this.name = n;
    }

    private int getAge() {
        return age;
    }

    private void setAge(String a) {
        int age = Integer.parseInt(a);
        if (age < 0) {
            throw new IllegalArgumentException(ErrorMessageContants.INVALID_INPUT_MESSAGE);
        }
        this.age = Integer.parseInt(a);
    }

    private String getGender() {
        if(this.gender) return MALE_GENDER;
        else return FEMALE_GENDER;
    }

    protected void setGender(String g) {
        if(!g.equals(FEMALE_GENDER) && !g.equals(MALE_GENDER)) {
            throw new IllegalArgumentException(ErrorMessageContants.INVALID_INPUT_MESSAGE);
        }
        switch (g.toLowerCase()){
            case "male": this.gender = true; break;
            case "female": this.gender = false; break;
        }
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName() +
                System.lineSeparator() +
                this.getName() + " " +
                this.getAge() + " " +
                this.getGender() + " " +
                System.lineSeparator() +
                this.produceSound();
    }

    public String produceSound() {
        return SOUND;
    }
}


