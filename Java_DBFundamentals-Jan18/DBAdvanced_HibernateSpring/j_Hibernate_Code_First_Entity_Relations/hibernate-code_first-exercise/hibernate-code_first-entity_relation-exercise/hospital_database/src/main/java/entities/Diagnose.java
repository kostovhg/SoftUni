package entities;

import javax.persistence.*;

@Entity
@Table(name = "diagnoses")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    private Patient patient;

    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
    }

    public Diagnose() {
    }
}
