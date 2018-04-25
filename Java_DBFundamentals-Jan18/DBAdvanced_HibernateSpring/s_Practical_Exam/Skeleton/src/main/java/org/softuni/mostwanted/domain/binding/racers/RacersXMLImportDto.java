package org.softuni.mostwanted.domain.binding.racers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacersXMLImportDto {

    @XmlElement()
    private String name;
}
