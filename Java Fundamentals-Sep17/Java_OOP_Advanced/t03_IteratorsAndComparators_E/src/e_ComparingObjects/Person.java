package e_ComparingObjects;

public class Person implements Comparable<Person>{

    private String name;
    private int age;
    private String town;

    public Person(String... input) {
        this.name = input[0];
        this.age = Integer.parseInt(input[1]);
        this.town = input[2];
    }

    @Override
    public int compareTo(Person otherPerson) {
        String thisName = this.name;
        String otherName = otherPerson.name;

        if(thisName.equals(otherName)){
            int thisAge= this.age;
            int otherAge= otherPerson.age;
            if(thisAge == otherAge){
                return this.town.compareTo(otherPerson.town);
            } else {
                return Integer.compare(thisAge, otherAge);
            }
        }
        return thisName.compareTo(otherName);
    }
}
