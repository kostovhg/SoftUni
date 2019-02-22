package casebook.web.beans;

import casebook.domain.models.view.UserFriendsViewModel;
import casebook.service.UserService;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("userFriendsBean")
@RequestScoped
public class UserFriendsBean extends BaseBean {

    private List<UserFriendsViewModel> friends;

    public UserFriendsBean() {
    }

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
    }

    @PostConstruct
    public void init() {
//        this.friends = this.userService.getUserById(this.getSession().getAttribute("user-id").toString())
//                .getFriends()
//                .stream()
//                .map(u -> this.modelMapper.map(u, UserFriendsViewModel.class))
//                .collect(Collectors.toList());
        this.friends = mapper.convert(userService.getUserById(this.getSession().getAttribute("user-id").toString()).getFriends(), UserFriendsViewModel.class);
    }

    public List<UserFriendsViewModel> getFriends() {
        return this.friends;
    }

    public void setFriends(List<UserFriendsViewModel> friends) {
        this.friends = friends;
    }

    public void unfrend(String id) {


//        this.redirect("/home");
    }


}