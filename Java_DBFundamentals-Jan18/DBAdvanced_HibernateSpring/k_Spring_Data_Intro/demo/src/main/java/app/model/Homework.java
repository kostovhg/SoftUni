package app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "homeworks")
public class Homework {

    private long id;

    private String content;

    private LocalDate submissionDate;

    private Course course;

    private Student student;

    public Homework() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "subission_date", nullable = false)
    public LocalDate getSubissionDate() {
        return this.submissionDate;
    }

    public void setSubissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
