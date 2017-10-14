import java.util.ArrayList;

public class Student {
    private String fNum;
    private String firstName;
    private String familyName;
    private String email;
    private Integer age;
    private Integer group;
    private ArrayList<Integer> grades;
    private String phone;

    public Student(){}

    public Student(String line){
        String[] tokens = line.split("\\t+");
        this.fNum = tokens[0];
        this.firstName = tokens[1];
        this.familyName = tokens[2];
        this.email = tokens[3];
        this.age = Integer.valueOf(tokens[4]);
        this.group = Integer.valueOf(tokens[5]);
        this.grades = new ArrayList<>();
        for (int i = 6; i < 10; i++) {
            grades.add(Integer.valueOf(tokens[i]));
        }
        this.phone = tokens[10];

    }


    public String getfNum() {
        return fNum;
    }

    public void setfNum(String fNum) {
        this.fNum = fNum;
    }

    public String getFirstName() {return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() { return familyName; }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

