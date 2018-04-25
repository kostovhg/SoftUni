package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {

    @Id
    private String serialNumber;

    @OneToOne(mappedBy="passport")
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "owner_phone_number", nullable = false)
    private String ownerPhoneNumber;

    @Column(name = "owner_name", nullable = false, length = 20)
    private String ownerName;

    @Column(name = "registered_on", nullable = false)
    private Date registrationDate;

    public Passport() {
    }

    // – a string consisting of exactly 10 characters and ending with 3 digits, primary identification field
    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    // – the animal to which the passport is registered
    public Animal getAnimal() {
        return this.animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    // – the phone number of the animal’s owner, required, make sure it matches the following requirements:
    //either starts with +359 and 9 digits following
    //consists of exacly 10 digits, starting with 0
    public String getOwnerPhoneNumber() {
        return this.ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    // – the name of the animal’s owner; minimum size of 3 characters and maximum 30
    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    // – the date on which the passport was registered
    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
