package p10_FamilyTree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static Map<Integer, Person> personsByName = new LinkedHashMap<>();
    public static Map<Integer, Person> personsByDate = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer counter = 0;
        String line = reader.readLine();
        String firstPerson = line;
        while(!(line = reader.readLine()).equals("End")) {
            if (inputIsParentChild(line)) {
                String[] tokens = line.split("\\s+-\\s+");
                Person parent;
                Person child;

                if(!isDate(tokens[0])) {
                    if (!containsPerson(personsByName, tokens[0])) {
                        personsByName.put(counter++, new Person(tokens[0]));
                    }
                    parent = returnPerson(personsByName, tokens[0]);
                } else {
                    if (!containsPerson(personsByDate, tokens[0])) {
                        personsByDate.put(counter++, new Person(tokens[0]));
                    }
                    parent = returnPerson(personsByDate, tokens[0]);
                }
                if(!isDate(tokens[1])) {
                    if(!containsPerson(personsByName, tokens[1])){
                        personsByName.put(counter++, new Person(tokens[1]));
                    }
                    child = returnPerson(personsByName, tokens[1]);
                } else {
                    if(!containsPerson(personsByDate, tokens[1])) {
                        personsByDate.put(counter++, new Person(tokens[1]));
                    }
                    child = returnPerson(personsByDate, tokens[1]);
                }
                parent.addChild(child);
                child.addParent(parent);

            } else {
                String personData = line.substring(line.lastIndexOf(" ") + 1);
                String personName = line.substring(0, line.lastIndexOf(" "));
                if(!containsPerson(personsByName, personName)) {
                    personsByName.put(counter++, new Person(personName));
                }
                if(!containsPerson(personsByDate, personData)) {
                    personsByDate.put(counter++, new Person(personData));
                }
                returnPerson(personsByName, personName).setDate(personData);
                returnPerson(personsByDate, personData).setName(personName);
            }
        }

        //Map<String, Person> persons = combineMaps(personsByName, personsByDate);
        Person person = returnPerson(personsByDate, firstPerson);
        Person person2 = returnPerson(personsByName, firstPerson);
        System.out.println(person.toString());
        System.out.println("Parents:");
        if(person2.getParents().size() > person.getParents().size()) {
            for (Person p :
                    person2.getParents()) {
                System.out.println(p.toString());
            }
        } else {
            for (Person p :
                    person.getParents()) {
                System.out.println(p.toString());
            }
        }
        System.out.println("Children:");
        if(person2.getChildren().size() >person.getChildren().size()) {
            for (Person p :
                    person2.getChildren()) {
                System.out.println(p.toString());
            }
        } else {
            for (Person p :
                    person.getChildren()) {
                System.out.println(p.toString());
            }
        }

    }

    private static Map<String, Person> combineMaps(Map<Integer, Person> names, Map<Integer, Person> dates) {
           Map<String, Person> result = new LinkedHashMap<>();
        for (int i = 0; i < names.size() + dates.size() ; i++) {
            Person fromNames = null;
            Person fromDates = null;
            if(names.containsKey(i)){
                fromNames = names.get(i);
            } else {
                fromDates = dates.get(i);
            }
            if(fromNames != null) {
                fromDates = returnPerson(dates, fromNames.getDate());
            } else {
                fromNames = returnPerson(names, fromDates.getName());
            }
        }
        return null;
    }

    private static Person returnPerson(Map<Integer, Person> map, String person) {
        Person res = null;
        for (Person p :
                map.values()) {
            if (p.found(person)) {
                res = p;
                break;
            }
        }
        return res;
    }

    private static boolean containsPerson(Map<Integer, Person> map, String str) {
        return map.values().stream().anyMatch(p -> p.found(str));
    }

    private static boolean isDate(String parent) {
        return parent.contains("/");
    }

    private static boolean inputIsParentChild(String line) {
        return line.contains(" - ");
    }
}

class Person {
    private String name = "";
    private String birthDate = "";
    private Set<Person> parent;
    private Set<Person> children;

    Person(String input) {
        if(input.contains("/")){
            this.birthDate = input.trim();
        } else {
            this.name = input.trim();
        }
        parent = new LinkedHashSet<>();
        children = new LinkedHashSet<>();
    }

    public void addParent(Person person){
        parent.add(person);
    }

    public Set<Person> getParents(){
        return this.parent;
    }

    public void addChild(Person person) {
        children.add(person);
    }

    public Set<Person> getChildren() {
        return this.children;
    }

    public void setDate(String str){
        birthDate = str;
    }

    public String getDate(){
        return this.birthDate;
    }

    public void setName(String str) {
        name = str;
    }

    public String getName(){
        return this.name;
    }

    public Person returnPerson(String str){
        if(this.birthDate.equals(str) || this.name.equals(str)){
            return this;
        }
        else return null;
    }

    /*@Override
    public int hashCode() {
        return name.hashCode() + birthDate.hashCode();
    }*/

    @Override
    public boolean equals(Object otherPerson) {
        Person test = (Person) otherPerson;
        return this.birthDate.equals(test.birthDate) ||
                this.name.equals(test.name);
    }

    public boolean found(String str) {
        return this.birthDate.equals(str.trim()) ||
                this.name.trim().equals(str.trim());
    }

    @Override
    public String toString() {
        String sep = "";
        if(!this.name.equals("") && !this.birthDate.equals("")) sep = " ";
        return String.format("%s%s%s",
                this.name,
                sep,
                this.birthDate);
    }

    private String listToString(Set<Person> set) {
        StringBuilder output = new StringBuilder();
        for (Person person :
                set) {
            output.append(person.toString()).append("\n");
        }
        return output.toString();
    }

    public void print(){
        System.out.println(String.format("%s%nParents:%n%sChildren:%n%s",
                this.toString(),
                listToString(parent),
                listToString(children)
                ));
    }
}

