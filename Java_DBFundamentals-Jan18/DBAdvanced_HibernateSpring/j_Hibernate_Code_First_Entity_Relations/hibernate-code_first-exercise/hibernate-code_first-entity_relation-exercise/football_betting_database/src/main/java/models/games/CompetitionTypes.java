package models.games;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competition_type")
public class CompetitionTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type")
    private Set<Competition> competitions;
}
