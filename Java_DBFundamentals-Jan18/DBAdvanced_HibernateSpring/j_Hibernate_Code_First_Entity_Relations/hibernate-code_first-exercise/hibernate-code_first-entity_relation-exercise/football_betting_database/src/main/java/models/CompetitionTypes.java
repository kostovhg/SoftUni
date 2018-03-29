package models;
import javax.persistence.*;

@Entity
@Table(name = "competition_type")
public class CompetitionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = " name")
    private String name;
}
