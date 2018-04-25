package app.retake.domain.dto;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO {

    @XmlElement(name = "vet")
    private List<VetXMLImportDTO> vets;

    public VetWrapperXMLImportDTO() {
        this.vets = new ArrayList<>();
    }

    public List<VetXMLImportDTO> getVets() {
        return this.vets;
    }

    public void setVets(List<VetXMLImportDTO> vets) {
        this.vets = vets;
    }
}
