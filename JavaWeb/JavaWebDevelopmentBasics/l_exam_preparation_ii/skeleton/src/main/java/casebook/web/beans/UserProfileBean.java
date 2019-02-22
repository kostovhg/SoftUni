package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserProfileViewModel;
import casebook.service.UserService;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("userProfileBean")
@RequestScoped
public class UserProfileBean extends BaseBean {

    private UserProfileViewModel model;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper modelMapper) {
        super(userService, modelMapper);
    }

    @PostConstruct
    public void init() {
        String id = super.getExternalContext().getRequestParameterMap().get("id");

        UserServiceModel userServiceModel = this.userService.getUserById(id);

        if (userServiceModel == null) {
            throw new IllegalArgumentException("There is no user with that id");
        }

        this.model = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
    }

    public UserProfileViewModel getModel() {
        return this.model;
    }

    public void setModel(UserProfileViewModel model) {
        this.model = model;
    }
}