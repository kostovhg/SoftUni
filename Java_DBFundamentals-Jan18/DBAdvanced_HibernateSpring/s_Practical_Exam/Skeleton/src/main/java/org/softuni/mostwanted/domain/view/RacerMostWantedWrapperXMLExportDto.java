package org.softuni.mostwanted.domain.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerMostWantedWrapperXMLExportDto {

    @XmlElement(name = "racer")
    private RacerXMLExportDto racer;

    public RacerMostWantedWrapperXMLExportDto() {
    }

    public RacerXMLExportDto getRacer() {
        return this.racer;
    }

    public void setRacer(RacerXMLExportDto racer) {
        this.racer = racer;
    }
}
