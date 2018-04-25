package org.softuni.mostwanted.domain.binding.entries;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesXMLImportDto implements Serializable {

    @XmlAttribute(name = "has-finished")
    private String hasFinished;

    @XmlAttribute(name = "finish-time")
    private String finishTime;

    @XmlAttribute(name = "car-id")
    private String carId;

    @XmlElement(name = "racer")
    private String racerName;

    public RaceEntriesXMLImportDto() {
    }

    public String getHasFinished() {
        return this.hasFinished;
    }

    public void setHasFinished(String hasFinished) {
        this.hasFinished = hasFinished;
    }

    public String getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRacerName() {
        return this.racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}