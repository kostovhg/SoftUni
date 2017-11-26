package g_EqualityLogic;

public class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String... personData) {
        this.setName(personData[0]);
        this.setAge(personData[1]);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    @Override
    public int compareTo(Person otherPerson) {
        String thisName = this.name;
        String otherName = otherPerson.name;

        if (thisName.equals(otherName)) {
            return Integer.compare(this.age, otherPerson.age);
        }
        return thisName.compareTo(otherName);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.name.hashCode();
        hash = hash * 31 + this.age;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }
        if (this == obj) return true;
        Person otherPerson = (Person) obj;
        return this.name.equals(otherPerson.name) && this.age == otherPerson.age;
    }

    @Override
    public String toString() {
        return String.format("%s %d", this.name, this.age);
    }
}
