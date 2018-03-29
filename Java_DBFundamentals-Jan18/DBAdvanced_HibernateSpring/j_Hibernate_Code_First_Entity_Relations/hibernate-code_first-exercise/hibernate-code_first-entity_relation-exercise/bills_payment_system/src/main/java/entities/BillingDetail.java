package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "number")
    private long number;

    @ManyToOne
    private User owner;

    public BillingDetail(){}

    public BillingDetail(long number, User owner) {
        this.number= number;
        this.owner = owner;
    }
}
