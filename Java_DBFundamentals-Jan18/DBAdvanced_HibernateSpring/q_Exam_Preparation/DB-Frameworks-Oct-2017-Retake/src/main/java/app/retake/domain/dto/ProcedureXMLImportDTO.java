package app.retake.domain.dto;

import app.retake.parser.XMLParser;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {

    @XmlElement(name = "vet")
    private String vet;

    @XmlElement
    private String animal;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO> animalAids;

    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(XMLParser.DateTimeAdapter.class)
    private Date date;

    public ProcedureXMLImportDTO() {
        this.animalAids = new ArrayList<>();
    }

    public String getVet() {
        return this.vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return this.animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<ProcedureAnimalAidXMLImportDTO> getAnimalAids() {
        return this.animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLImportDTO> animalAids) {
        this.animalAids = animalAids;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
