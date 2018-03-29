package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visitations")
public class Visitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    private Patient patient;

    public Visitation(Date date, String comments) {
        this.date = date;
        this.comments = comments;
    }

    public Visitation() {
    }
}
