package org.softuni.ruk.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch {

    private Integer id; //– integer number, primary identification field.
    private String name; //– a string (required).
    private Set<Employee> employees;

    public Branch() {
        this.employees = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy="branch", cascade=CascadeType.ALL, orphanRemoval=true)
    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
