package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person {

    @Column(name = "average_grade")
    private BigDecimal averageGrade;

    @Column(name = "attendance")
    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns =
            @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Student(){}

    public Student(String firstName, String lastName, String phoneNumber, BigDecimal averageGrade, int attendance, Set<Course> courses) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.courses = courses;
    }
}

