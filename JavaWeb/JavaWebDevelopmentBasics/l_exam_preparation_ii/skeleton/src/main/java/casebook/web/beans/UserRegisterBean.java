package casebook.web.beans;

import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userRegisterBean")
@RequestScoped
public class UserRegisterBean extends BaseBean {

    private UserRegisterBindingModel model;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
        //this.init();
    }

    @PostConstruct
    public void init() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.model = userRegisterBindingModel;
    }

    public void register() {
        if (!this.model.getPassword().equals(this.model.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords does not match");
        }

        if (!this.userService.registerUser(this.modelMapper.map(this.model, UserServiceModel.class))) {
            throw new IllegalArgumentException("User were not registered");
        }

        super.redirect("/login");

    }
}