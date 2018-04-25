package org.softuni.mostwanted.domain.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerEntryXMLExportDto implements Serializable {

    @XmlElement(name = "finish-time")
    private String finishTime;

    @XmlElement(name = "car")
    private String car;

    public RacerEntryXMLExportDto() {
    }

    public RacerEntryXMLExportDto(String finishTime, String car) {
        this.finishTime = finishTime;
        this.car = car;
    }

    public String getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCar() {
        return this.car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
