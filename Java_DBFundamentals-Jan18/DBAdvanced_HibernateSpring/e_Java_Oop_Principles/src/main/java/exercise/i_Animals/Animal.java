package exercise.i_Animals;

public abstract class Animal implements NoiceProducing {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, String age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(String age) {
        int parsedAge = Integer.parseInt(age);
        if(parsedAge <= 0 ){
            throw new IllegalArgumentException("Invalid input!");
        } else {
            this.age = parsedAge;
        }
    }

    protected void setGender(String gender) {
        this.gender = gender;
    }

    //public abstract String produceSound();

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s%n%s",
                this.getClass().getSimpleName(),
                this.name, this.age, this.gender, this.produceSound());
    }

    @Override
    public String produceSound() {
        Class aClass = this.getClass();
        String className = aClass.getSimpleName();
        return Classes.valueOf(className.toUpperCase()).getSound();
    }
}
