package org.softuni.mostwanted.domain.binding.races;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImportDto {

    /*
    <race>
        <laps>4</laps>
        <district-name>Jackson</district-name>
        <entries>
            <entry id="10"/>
            <entry id="9"/>
        </entries>
     */

    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElement(name = "entries")
    private RaceXMLRaceEntriesWrapperDto raceEntries;

    public RaceXMLImportDto() {
    }

    public Integer getLaps() {
        return this.laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

//    public List<RaceEntryByIdDto> getRaceEntries() {
//        return this.raceEntries;
//    }
//
//    public void setRaceEntries(List<RaceEntryByIdDto> raceEntries) {
//        this.raceEntries = raceEntries;
//    }

    public RaceXMLRaceEntriesWrapperDto getRaceEntries() {
        return this.raceEntries;
    }

    public void setRaceEntries(RaceXMLRaceEntriesWrapperDto raceEntries) {
        this.raceEntries = raceEntries;
    }
}
