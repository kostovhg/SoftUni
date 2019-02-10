package metube.web.filters;

import metube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Filter is used to bind the form data to model on post request
 */

@WebFilter("/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post")){
            UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();
            userRegisterBindingModel.setUsername(req.getParameter("username"));
            userRegisterBindingModel.setPassword(req.getParameter("password"));
            userRegisterBindingModel.setConfirmPassword(req.getParameter("confirmPassword"));
            userRegisterBindingModel.setEmail(req.getParameter("email"));

            req.setAttribute("model", userRegisterBindingModel);
        }

        chain.doFilter(req, resp);
    }
}
