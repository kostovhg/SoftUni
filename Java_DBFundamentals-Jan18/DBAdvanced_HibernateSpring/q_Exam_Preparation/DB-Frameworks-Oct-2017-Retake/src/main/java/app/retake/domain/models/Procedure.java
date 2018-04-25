package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "procedures")
public class Procedure implements Serializable {

    /*
    id – integer value, primary indentification field
services – collection of services performed to the animal
animal – the animal to which the procedure is performed
cost – the cost of the procedure, calculated by sumating the cost of the different services performed; does not need to be inserted in the database
vet – the clinic’s employed doctor servicing the patient
date – the date on which the given procedure is performed
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(optional = false, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="animal_id", nullable = false, foreignKey=@ForeignKey(name = "fk_procedure_animal"))
    private Animal animal;

    @Transient
    private BigDecimal cost;

    @ManyToOne(optional = false, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="vet_id",foreignKey=@ForeignKey(name = "fk_procedure_vet"))
    private Vet vet;
    
    @Column(name = "date_performed")
    private Date datePerformed;

    @ManyToMany(mappedBy = "procedures")
    private Set<AnimalAid> services;

    public Procedure() {
        this.services = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public BigDecimal getCost() {
        return this.getServices().stream()
                .map(AnimalAid::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Vet getVet() {
        return this.vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDatePerformed() {
        return this.datePerformed;
    }

    public void setDatePerformed(Date datePerformed) {
        this.datePerformed = datePerformed;
    }

    public Set<AnimalAid> getServices() {
        return this.services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }
}
