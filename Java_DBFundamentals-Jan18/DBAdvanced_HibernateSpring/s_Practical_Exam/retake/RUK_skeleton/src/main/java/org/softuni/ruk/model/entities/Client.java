package org.softuni.ruk.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    private Integer id; //– integer number, primary identification field.
    private String fullName; //– a string (required).
    private int age; //– an integer number.
    private BankAccount bankAccount; //– a Bank Account entity (One).
    private List<Employee> employee; // on adding same client with different employee, change the employee of current client

    public Client() {
        this.employee = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "age")
    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_account_id")
    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @ManyToMany(mappedBy="clients", cascade = CascadeType.ALL)
    public List<Employee> getEmployee() {
        return this.employee;
    }

    public void changeAssignedEmployee(Employee employee){
        if(employee != null){
            if(this.employee.size() > 0) {
                this.employee.get(0).getClients().remove(this);
                this.employee.remove(0);
            }
            this.employee.add(employee);
            this.employee.get(0).getClients().add(this);
        }
    }

    public Employee checkAssignedEmployee(){
        if(this.employee.size() > 0){
            return this.employee.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Client)) return false;
        Client otherEntity = (Client) other;
        return this.getFullName().equalsIgnoreCase(otherEntity.getFullName());
    }

    @Override
    public int hashCode() {
        return this.getFullName().hashCode();
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
