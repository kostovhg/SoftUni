package app.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student{

    private long id;

    private String name;

    private String phoneNumber;

    private LocalDate registrationDate;

    private LocalDate birthDate;

    private Set<Course> courses;
    private Set<Homework> homeworks;

    public Student() {
    }

    public Student(long id, String name, String phoneNumber, LocalDate registrationDate, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "registration_date")
    public LocalDate getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "birth_date")
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses){
        this.courses = courses;
    }

    @OneToMany(mappedBy = "student")
    public Set<Homework> getHomeworks() {
        return this.homeworks;
    }

    public void setHomeworks(Set<Homework> homeworks){
        this.homeworks = homeworks;
    }
}
