package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Column(name = "email")
    private String email;

    @Column(name ="salary_per_hour")
    private BigDecimal salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> taughtCourses;

    public Teacher(){}

    public Teacher(String firstName, String lastName, String phoneNumber, String email, BigDecimal salaryPerHour, Set<Course> taughtCourses) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.taughtCourses = taughtCourses;
    }
}
