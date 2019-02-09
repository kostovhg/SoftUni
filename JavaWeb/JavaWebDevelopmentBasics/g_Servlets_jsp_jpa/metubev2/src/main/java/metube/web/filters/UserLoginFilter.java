package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;
import metube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post")){
            UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();
            userLoginBindingModel.setUsername(req.getParameter("username"));
            userLoginBindingModel.setPassword(req.getParameter("password"));

            req.setAttribute("model", userLoginBindingModel);
    } else if (req.getMethod().toLowerCase().equals("get") && req.getSession().getAttribute("username") != null){
            resp.sendRedirect("/home");
            return;
        }

        chain.doFilter(req, resp);
    }
}
