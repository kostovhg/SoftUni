package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLImportDTO {

    @XmlElement
    private String name;

    public ProcedureAnimalAidXMLImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
