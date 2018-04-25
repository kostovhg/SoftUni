package org.softuni.mostwanted.domain.binding.races;

import org.softuni.mostwanted.domain.binding.entries.RaceEntryByIdDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLRaceEntriesWrapperDto implements Serializable {

    @XmlElement(name = "entry")
    private List<RaceEntryByIdDto> entries;

    public RaceXMLRaceEntriesWrapperDto() {
        this.entries = new ArrayList<>();
    }

    public List<RaceEntryByIdDto> getEntries() {
        return this.entries;
    }

    public void setEntries(List<RaceEntryByIdDto> entries) {
        this.entries = entries;
    }
}
