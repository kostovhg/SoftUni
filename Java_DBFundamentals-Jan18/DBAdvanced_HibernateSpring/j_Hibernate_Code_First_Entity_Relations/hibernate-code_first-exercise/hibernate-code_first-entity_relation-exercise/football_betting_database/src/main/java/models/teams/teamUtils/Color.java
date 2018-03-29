package models.teams.teamUtils;

import javax.persistence.*;

@Entity
@Table(name = "colors")

public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    private String name;




}