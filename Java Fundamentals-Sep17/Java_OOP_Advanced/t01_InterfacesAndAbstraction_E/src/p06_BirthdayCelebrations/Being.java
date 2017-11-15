package p06_BirthdayCelebrations;

public abstract class Being implements Birthable {

    private String name;
    String birthdate;

    Being(String[] args){
        super();
        this.setName(args[1]);
        this.setBirthdate(args[args.length - 1]);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    @Override
    public String getBirthdate(){
        return this.birthdate;
    }
}
