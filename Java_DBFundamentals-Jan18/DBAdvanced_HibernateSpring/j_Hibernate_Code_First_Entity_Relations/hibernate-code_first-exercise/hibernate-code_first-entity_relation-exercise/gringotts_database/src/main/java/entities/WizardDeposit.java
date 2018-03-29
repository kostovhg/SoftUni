package entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "wizard_deposit")
public class WizardDeposit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  //– Primary Key (number in range [1, 231-1].

    @Column(name = "first_name", length = 50)
    private String firstName; //– Text field with max length of 50 symbols.

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;  //- Text field with max length of 60 symbols. Required

    @Column(name = "notes", length = 1000)
    private String notes;  //– Text field with max length of 1000 symbols

    @Column(name = "age", columnDefinition = "INT UNSIGNED NOT NULL")
    private int age;  //– Non-negative number. Required

    @Column(name = "magic_wand_creator", length = 100)
    private String magicWandCreator;  //– Text field with max length of 100 symbols

    @Column(name = "magic_wand_size", columnDefinition = "INT(11) UNSIGNED")
    private int magicWandSize;  //– Number in range [1, 215-1]

    @Column(name = "deposit_group", length = 20)
    private String depositGroup;  //- Text field with max length of 20 symbols

    @Column(name = "deposit_start_date")
    private Date depositStartDate;  //– Date and time field

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;  //– Floating point number field

    @Column(name = "deposit_interest")
    private String depositInterest;  //- Floating point number field

    @Column(name = "deposit_charge")
    private String depositCharge;  //- Floating point number field

    @Column(name = "deposit_expiration_date")
    private String depositExpirationDate;  //– Date and time field

    @Column(name = "is_deposit_expired")
    private boolean isDepositExpired;  //– Boolean field

    public WizardDeposit() {
        this.lastName = "";
    }

    public WizardDeposit(
            String firstName,
            String lastName,
            String notes,
            int age,
            String magicWandCreator,
            int magicWandSize,
            String depositGroup,
            Date depositStartDate,
            BigDecimal depositAmount,
            String depositInterest,
            String depositCharge,
            String depositExpiration_date,
            boolean isDepositExpired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.age = age;
        this.magicWandCreator = magicWandCreator;
        this.magicWandSize = magicWandSize;
        this.depositGroup = depositGroup;
        this.depositStartDate = depositStartDate;
        this.depositAmount = depositAmount;
        this.depositInterest = depositInterest;
        this.depositCharge = depositCharge;
        this.depositExpirationDate = depositExpiration_date;
        this.isDepositExpired = isDepositExpired;
    }
}