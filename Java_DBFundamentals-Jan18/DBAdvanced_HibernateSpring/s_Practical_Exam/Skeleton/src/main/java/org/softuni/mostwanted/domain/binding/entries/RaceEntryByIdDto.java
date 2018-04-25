package org.softuni.mostwanted.domain.binding.entries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryByIdDto implements Serializable {

    @XmlAttribute(name = "id")
    private String entryId;

    public RaceEntryByIdDto() {
    }

    public String getEntryId() {
        return this.entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }
}
