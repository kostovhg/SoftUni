package casebook.web.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("userLogoutBean")
@RequestScoped
public class UserLogoutBean extends BaseBean {

    public UserLogoutBean() {
    }

    public void logout() {
        //super.getSession().invalidate();
        super.getExternalContext().invalidateSession();
        super.redirect("/");
    }


}