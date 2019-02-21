package org.softuni.sboj.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("logoutBean")
@RequestScoped
public class LogoutBean extends BaseBean {

    public LogoutBean() {
    }

    public void logout() {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .invalidateSession();
        this.redirect("/index");
    }
}