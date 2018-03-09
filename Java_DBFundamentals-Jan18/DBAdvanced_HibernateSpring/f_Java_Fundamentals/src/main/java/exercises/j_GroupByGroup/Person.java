package exercises.j_GroupByGroup;

public class Person {

    private String name;
    private Integer group;

    public Person(String[] arg) {
        this.name = String.format("%s %s", arg[0], arg[1]);
        this.group = Integer.valueOf(arg[2]);
    }

    public String getName() {
        return this.name;
    }

    public Integer getGroup() {
        return this.group;
    }
}
