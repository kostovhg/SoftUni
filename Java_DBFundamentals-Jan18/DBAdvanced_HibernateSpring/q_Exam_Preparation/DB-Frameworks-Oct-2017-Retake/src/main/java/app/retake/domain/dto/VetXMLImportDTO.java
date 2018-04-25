package app.retake.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetXMLImportDTO {

    @Size(min = 3, max = 40)
    @XmlElement
    private String name;

    @Size(min = 3, max = 50)
    @XmlElement
    private String profession;

    @Min(22)
    @Max(65)
    @XmlElement
    private Integer age;

    @Pattern(regexp = "^\\+359\\d{9}|0\\d{9}$")
    @XmlElement(name = "phone-number")
    private String phone;

    public VetXMLImportDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
