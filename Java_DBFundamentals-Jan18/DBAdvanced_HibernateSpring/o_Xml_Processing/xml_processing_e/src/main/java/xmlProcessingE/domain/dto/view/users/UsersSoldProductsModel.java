package xmlProcessingE.domain.dto.view.users;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsModel {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<UserSoldProductModel> soldProducts;

    public UsersSoldProductsModel() {
        this.soldProducts = new ArrayList<>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<UserSoldProductModel> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<UserSoldProductModel> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public int getCount() {
        return 0;
    }
}
