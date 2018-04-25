package xmlProcessingE.domain.dto.binding.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsWrapperModel {

    @XmlElement(name = "product")
    private List<ProductCreateBindingModel> products;

    public ProductsWrapperModel() {
        this.products = new ArrayList<>();
    }

    public List<ProductCreateBindingModel> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductCreateBindingModel> users) {
        this.products = users;
    }
}
