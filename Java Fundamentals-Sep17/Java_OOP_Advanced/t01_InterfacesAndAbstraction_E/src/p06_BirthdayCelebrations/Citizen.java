package p06_BirthdayCelebrations;

public class Citizen extends Being {

    private String id;

    public Citizen(String[] args){
        super(args);
        this.setAge(args[2]);
        this.setId(args[3]);
    }

    private void setAge(String age) {
        int age1 = Integer.parseInt(age);
    }

    private void setId(String id) {
        this.id = id;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
