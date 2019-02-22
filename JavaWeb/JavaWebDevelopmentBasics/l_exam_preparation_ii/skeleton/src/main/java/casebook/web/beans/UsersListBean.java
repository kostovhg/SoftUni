package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserViewModel;
import casebook.service.UserService;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named("usersListBean")
@RequestScoped
public class UsersListBean extends BaseBean {

    private List<UserViewModel> users;

    public UsersListBean() {
    }

    @Inject
    public UsersListBean(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
    }

    @PostConstruct
    public void init() {
        String loggedInUserId = super.getSession().getAttribute("user-id").toString();
        this.users = this.userService.findAllUnknownUsers(loggedInUserId)
                .stream()
                .map(usm -> this.modelMapper.map(usm, UserViewModel.class))
                .collect(Collectors.toList());

        /*
        * Rado's method
        *
        String username = super.getSession().getAttribute("username").toString();
        UserServiceModel loggedInUser = this.userService.getUserById(loggedInUserId);
        this.users = this.userService
                .getAllUsers()
                .stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !loggedInUser.getFriends()
                                .stream()
                                .map(f -> f.getUsername())
                                .collect(Collectors.toList())
                                .contains(u.getUsername()))
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
                */
    }

    public List<UserViewModel> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }

    public void addFriend(String id) {
//        String currentUserId = this.getSession().getAttribute("user-id").toString();
//        try {
//            return super.userService.addFriend(currentUserId, id);
//        } catch (Exception e){
//            throw new IllegalArgumentException("That user is not available to be your friend.");
//        }

        /* Rado's method */

        UserServiceModel loggedInUser = this.userService.getUserById(this.getSession().getAttribute("user-id").toString());
        UserServiceModel friendCandidate = this.userService.getUserById(id);
        loggedInUser.getFriends().add(friendCandidate);

        if (!this.userService.addFriends(loggedInUser)) {
            throw new IllegalArgumentException("Server did not manage to add friend");
        }

        try {
            super.getExternalContext().redirect("/home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}