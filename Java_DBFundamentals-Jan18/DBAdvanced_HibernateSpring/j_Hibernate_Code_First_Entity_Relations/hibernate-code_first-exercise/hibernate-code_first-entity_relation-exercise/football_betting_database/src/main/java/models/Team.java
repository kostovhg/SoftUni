package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private int name;

    @Column(name = "logo", columnDefinition = "BLOB")
    private byte[] logo;

    @Column(name = "initials", columnDefinition = "VARCHAR(3) NOT NULL")
    private String initials;

    @ManyToOne(optional = false)
    @JoinColumn(name = "primary_kit_color_id", referencedColumnName = "id")
    private Color primaryKitColor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "secondary_kit_color_id", referencedColumnName = "id")
    private Color secondaryKitColor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    @Basic
    private BigDecimal budeg;

}
