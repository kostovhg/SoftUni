package org.softuni.ruk.model.dto.importXml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountXMLImportDTO implements Serializable {

    /*
     <bank-account client="Elyn Grimditch">
        <account-number>84999053-X</account-number>
        <balance>439216.96</balance>
    </bank-account>
     */

    @XmlAttribute(name = "client")
    private String clientNames;

    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "balance")
    private BigDecimal balance;

    public BankAccountXMLImportDTO() {
    }

    public String getClientNames() {
        return this.clientNames;
    }

    public void setClientNames(String clientNames) {
        this.clientNames = clientNames;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
