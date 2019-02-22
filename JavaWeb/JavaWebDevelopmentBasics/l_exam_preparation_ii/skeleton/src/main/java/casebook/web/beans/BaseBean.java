package casebook.web.beans;

import casebook.utils.ModelParser;
import casebook.utils.ModelParserImpl;
import org.modelmapper.ModelMapper;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import casebook.service.UserService;


public abstract class BaseBean {

    UserService userService;
    ModelMapper modelMapper;
    ModelParser mapper;

    BaseBean() {
    }

    BaseBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.mapper = new ModelParserImpl();
    }

    void redirect(String url) {

        try {
            this.getExternalContext().redirect(url);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    HttpSession getSession(){
        return (HttpSession) this.getExternalContext().getSession(true);
    }

    ExternalContext getExternalContext(){
        return FacesContext.getCurrentInstance().getExternalContext();
    }
}
