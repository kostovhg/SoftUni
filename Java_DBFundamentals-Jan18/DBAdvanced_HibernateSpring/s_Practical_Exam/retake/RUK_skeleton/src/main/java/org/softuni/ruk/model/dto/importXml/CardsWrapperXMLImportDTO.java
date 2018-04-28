package org.softuni.ruk.model.dto.importXml;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardsWrapperXMLImportDTO {

    @XmlElement(name = "card")
    private List<CardXMLImportDTO> cards;

    public CardsWrapperXMLImportDTO() {
        this.cards = new ArrayList<>();
    }

    public List<CardXMLImportDTO> getCards() {
        return this.cards;
    }

    public void setCards(List<CardXMLImportDTO> cards) {
        this.cards = cards;
    }
}