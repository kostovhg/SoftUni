package org.softuni.sboj.web.beans;


import javax.faces.context.FacesContext;
import java.io.IOException;

public class BaseBean {

    protected void redirect(String url) {

        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(String.format("/views%s.jsf", url));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
