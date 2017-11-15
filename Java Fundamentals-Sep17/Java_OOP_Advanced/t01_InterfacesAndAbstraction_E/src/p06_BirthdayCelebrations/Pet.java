package p06_BirthdayCelebrations;

public class Pet extends Being {

    public Pet(String[] args){
        super(args);
    }

    @Override
    public String getBirthdate(){
        return this.birthdate;
    }

    @Override
    public String getId() {
        return null;
    }
}
