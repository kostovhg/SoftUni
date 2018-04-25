package org.softuni.mostwanted.domain.binding.entries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesWrapperXMLImport implements Serializable {

    @XmlElement(name="race-entry")
    private List<RaceEntriesXMLImportDto> raceEntries;

    public RaceEntriesWrapperXMLImport() {
        this.raceEntries = new ArrayList<>();
    }

    public List<RaceEntriesXMLImportDto> getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(List<RaceEntriesXMLImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
