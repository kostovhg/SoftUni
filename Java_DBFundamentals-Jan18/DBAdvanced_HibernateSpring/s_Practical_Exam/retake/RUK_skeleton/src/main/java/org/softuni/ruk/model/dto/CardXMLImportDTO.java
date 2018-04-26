package org.softuni.ruk.model.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardXMLImportDTO implements Serializable {

    @XmlAttribute(name = "status")
    private String cardStatus;

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "card-number")
    private String cardNumber;

    public CardXMLImportDTO() {
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}