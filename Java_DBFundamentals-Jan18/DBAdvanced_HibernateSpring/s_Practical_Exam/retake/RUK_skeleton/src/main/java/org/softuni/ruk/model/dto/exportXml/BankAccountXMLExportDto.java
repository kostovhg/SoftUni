package org.softuni.ruk.model.dto.exportXml;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountXMLExportDto implements Serializable {

    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<CardXMLExportDto> cards;

    public BankAccountXMLExportDto() {
        this.cards = new ArrayList<>();
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<CardXMLExportDto> getCards() {
        return this.cards;
    }

    public void setCards(List<CardXMLExportDto> cards) {
        this.cards = cards;
    }
}
