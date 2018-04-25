package org.softuni.mostwanted.domain.binding.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLWrapperDto {

    @XmlElement(name = "race")
    private List<RaceXMLImportDto> races;

    public RaceXMLWrapperDto() {
        this.races = new ArrayList<>();
    }

    public List<RaceXMLImportDto> getRaces() {
        return this.races;
    }

    public void setRaces(List<RaceXMLImportDto> races) {
        this.races = races;
    }
}
