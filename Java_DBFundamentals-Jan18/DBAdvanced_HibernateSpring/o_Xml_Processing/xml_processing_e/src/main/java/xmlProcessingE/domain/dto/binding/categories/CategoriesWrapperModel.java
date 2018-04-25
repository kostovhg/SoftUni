package xmlProcessingE.domain.dto.binding.categories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesWrapperModel {

    @XmlElement(name = "category")
    private List<CategoryCreateBindingModel> categories;

    public CategoriesWrapperModel() {
        this.categories = new ArrayList<>();
    }

    public List<CategoryCreateBindingModel> getCategories() {
        return this.categories;
    }

    public void setCategories(List<CategoryCreateBindingModel> users) {
        this.categories = users;
    }
}
