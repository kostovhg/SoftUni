package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "has_medical_insurance")
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @OneToMany(mappedBy = "patient")
    private Set<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<Medicament> medicaments;

    public Patient(String firstName, String lastName, String address, String email, Date dateOfBirth, byte[] picture, boolean hasMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Patient() {
    }
}
