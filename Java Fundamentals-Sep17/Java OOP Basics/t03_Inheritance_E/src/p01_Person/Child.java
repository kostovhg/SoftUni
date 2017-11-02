package p01_Person;

public class Child extends Person{

    private Child(String childName, int childAge) {
        super(childName, childAge);

    }

    @Override
    public void setAge(int childAge) throws IllegalArgumentException {
        if (childAge > 15) {
            throw new IllegalArgumentException(" Child's age must be lesser than 15!");
        }
        super.setAge(childAge);
    }
}
