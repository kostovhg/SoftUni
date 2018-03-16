package exercise.f_BirthdayCelebrations;

public class BaseBeing implements Born {

    private String name;
    private String birthday;

    public BaseBeing(String name, String birthday) {
        this.setName(name);
        this.setBirthday(birthday);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public boolean validateBirthYear(String year) {
        return this.birthday.endsWith(year);
    }
}
