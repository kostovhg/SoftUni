package xmlProcessingE.domain.dto.view.users;

import xmlProcessingE.domain.dto.binding.user.UsersWrapperModel;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsWrapper {


    @XmlElement(name = "user")
    private List<UserModel> users;

    public UsersSoldProductsWrapper() {
        this.users = new ArrayList<>();
    }

    public List<UserModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
