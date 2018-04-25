package xmlProcessingE.domain.dto.binding.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWrapperModel {

    @XmlElement(name = "user")
    private List<UserCreateBindingModel> users;

    public UsersWrapperModel() {
        this.users = new ArrayList<>();
    }

    public List<UserCreateBindingModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserCreateBindingModel> users) {
        this.users = users;
    }
}
