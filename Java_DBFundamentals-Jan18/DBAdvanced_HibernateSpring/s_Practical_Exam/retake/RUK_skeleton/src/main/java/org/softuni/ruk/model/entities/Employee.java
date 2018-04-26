package org.softuni.ruk.model.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    private Integer id; //– integer number, primary identification field.
    private String firstName; //– a string (required).
    private String lastName; //– a string (required).
    private BigDecimal salary; //– a decimal data type.
    private Date startedOn; //– a Date. // localdate
    private Branch branch; //– a Branch entity (required).
    private List<Client> clients; //– a collection of Client entity.

    public Employee() {
        this.clients = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "fist_name", nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "salary", scale = 2, precision = 10)
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column(name = "started_on")
    public Date getStartedOn() {
        return this.startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    //@Column(name = "branch", nullable = false) // for the test
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch")
    public Branch getBranch() {
        return this.branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    //@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employees_clients",
            joinColumns = @JoinColumn(name = "employees_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "clients_id", referencedColumnName = "id"))
    public List<Client> getClients() {
        return this.clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client){
        if(client != null){
            this.clients.add(client);
        }
    }

    public void removeClient(Client client){
        if(client != null){
            this.clients.remove(client);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Employee)) return false;
        Employee otherEntity = (Employee) other;
        return this.getFirstName().equalsIgnoreCase(otherEntity.getFirstName()) && this.getLastName().equalsIgnoreCase(otherEntity.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
