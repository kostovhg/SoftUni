package xmlProcessingE.domain.dto.view.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeWrapper {

    @XmlElement(name = "product")
    private List<ProductsInRangeModel> products;

    public ProductsInRangeWrapper() {
        this.products = new ArrayList<>();
    }

    public List<ProductsInRangeModel> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsInRangeModel> products) {
        this.products = products;
    }
}
