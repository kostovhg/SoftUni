package casebook.web.beans;

import casebook.domain.models.binding.UserLoginBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userLoginBean")
@RequestScoped
public class UserLoginBean extends BaseBean {

    private UserLoginBindingModel model;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
        this.init();
    }

    @PostConstruct
    public void init() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserLoginBindingModel userRegisterBindingModel) {
        this.model = userRegisterBindingModel;
    }

    public void login() {

//        UserServiceModel user = this.userService.findByUsername(this.model.getUsername());
//        if(user == null) {
//            throw new IllegalArgumentException("There is no such user.");
//        }
//
//        if (!DigestUtils.sha256Hex(this.model.getPassword()).equals(user.getPassword())) {
//            throw new IllegalArgumentException("Passwords does not match");
//            // or super.redirect("/login");
//        }

        UserServiceModel userServiceModel = this.userService
                .loginUser(this.modelMapper.map(this.model, UserServiceModel.class));

        if(userServiceModel == null){
            throw new IllegalArgumentException("Something went wrong!");
            //super.redirect("/login");
        }

        super.getSession().setAttribute("username", userServiceModel.getUsername());
        super.getSession().setAttribute("user-id", userServiceModel.getId());

        super.redirect("/home");

    }
}