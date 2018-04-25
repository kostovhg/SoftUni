package xmlProcessingE.domain.dto.binding.categories;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryCreateBindingModel implements Serializable {

    @XmlElement(name = "name")
    private String name;

    public CategoryCreateBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
