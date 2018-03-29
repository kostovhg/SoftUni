package entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name =  "phone_number")
    private String phoneNumber;

    public Person(){}

    public Person(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
