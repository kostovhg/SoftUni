package org.softuni.ruk.model.dto.exportXml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "family-guy")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientXMLExportDto implements Serializable {

    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute(name = "age")
    private String age;

    @XmlElement(name = "bank-account")
    private BankAccountXMLExportDto bankAccount;

    public ClientXMLExportDto() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public BankAccountXMLExportDto getBankAccount() {
        return this.bankAccount;
    }

    public void setBankAccount(BankAccountXMLExportDto bankAccount) {
        this.bankAccount = bankAccount;
    }
}
