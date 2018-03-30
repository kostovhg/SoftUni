package app.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    private long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal price;

    private Set<Student> students;

    private Set<Homework> homework;

    public Course() {
    }

    public Course(long id, String name, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_date", nullable = false)
    public LocalDate getRegistrationDate() {
        return this.startDate;
    }

    public void setRegistrationDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date", nullable = false)
    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "courses_students",
        joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students){
        this.students = students;
    }

    @OneToMany(mappedBy = "course")
    public Set<Homework> getHomework() {
        return this.homework;
    }

    public void setHomework(Set<Homework> homework){
        this.homework = homework;
    }
}
