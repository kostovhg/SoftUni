package models.games;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_type_id")
    private CompetitionTypes type;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;
}
