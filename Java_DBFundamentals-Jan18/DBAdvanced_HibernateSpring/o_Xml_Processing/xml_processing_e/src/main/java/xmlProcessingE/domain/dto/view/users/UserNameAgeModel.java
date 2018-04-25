package xmlProcessingE.domain.dto.view.users;

import javax.xml.bind.annotation.*;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class UserNameAgeModel implements Comparable<UserNameAgeModel> {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "sold-products")
    private UsersSoldProductsModel soldProducts;

    public UserNameAgeModel() {
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UsersSoldProductsModel getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(UsersSoldProductsModel soldProducts) {
        this.soldProducts = soldProducts;
    }


    @Override
    public int compareTo(UserNameAgeModel userNameAgeModel) {
        int c = Integer.compare(userNameAgeModel.getSoldProducts().getCount(), this.getSoldProducts().getCount());
        if(c == 0) {
            c = this.getLastName().compareTo(userNameAgeModel.getLastName());
        }

        return c;
    }
}
