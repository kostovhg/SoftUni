package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String SWIFTCode;

    public BankAccount(long number, User owner, String bankName, String SWIFTCode) {
        super(number, owner);
        this.bankName = bankName;
        this.SWIFTCode = SWIFTCode;
    }

    public BankAccount() {}

    public BankAccount(long number, User owner) {
        super(number, owner);
    }
}
