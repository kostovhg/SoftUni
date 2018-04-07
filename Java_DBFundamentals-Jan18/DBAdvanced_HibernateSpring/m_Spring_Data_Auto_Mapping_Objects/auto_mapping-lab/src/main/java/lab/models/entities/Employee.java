package lab.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private BigDecimal salary;
    private Boolean isOnHoliday;
    private Address address;
    private Employee manager;
    private Set<Employee> managedEmployees;

    public Employee() {
        this.managedEmployees = new HashSet<>();
    }

    public Employee(String firstName, String lastName, Address address) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Employee(String firstName, String lastName, Address address, Date birthDate) {
        this(firstName, lastName, address);
        this.birthDate = birthDate;
    }

    public Employee(String firstName, String lastName, Address address, BigDecimal salary) {
        this(firstName, lastName, address);
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, Address address, Date birthDate, BigDecimal salary) {
        this(firstName, lastName, address, birthDate);
        this.salary = salary;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11) UNSIGNED")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(columnDefinition = "DECIMAL(19, 2)")
    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Basic
    public Boolean getOnHoliday() {
        return this.isOnHoliday;
    }

    public void setOnHoliday(Boolean onHoliday) {
        this.isOnHoliday = onHoliday;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id")
    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "manager", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public Set<Employee> getManagedEmployees() {
        return this.managedEmployees;
    }

    public void setManagedEmployees(Set<Employee> managedEmployees) {
        this.managedEmployees =managedEmployees;
    }

    public void addManagedEmployee(Employee employee){
        this.managedEmployees.add(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + this.id +
                ", firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", salary=" + this.salary +
                ", birthDate=" + this.birthDate +
                ", address=" + this.address +
                '}';
    }
}
