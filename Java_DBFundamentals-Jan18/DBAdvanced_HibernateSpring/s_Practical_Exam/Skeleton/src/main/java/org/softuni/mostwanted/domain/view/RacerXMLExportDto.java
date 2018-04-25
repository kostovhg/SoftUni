package org.softuni.mostwanted.domain.view;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerXMLExportDto implements Serializable {

    @XmlAttribute(name = "name")
    private String racerName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RacerEntryXMLExportDto> entries;

    public RacerXMLExportDto() {
        this.entries = new ArrayList<>();
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }

    public List<RacerEntryXMLExportDto> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RacerEntryXMLExportDto> entries) {
        this.entries = entries;
    }
}
