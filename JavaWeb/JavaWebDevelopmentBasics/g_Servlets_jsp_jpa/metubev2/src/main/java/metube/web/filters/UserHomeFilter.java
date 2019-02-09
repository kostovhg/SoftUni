package metube.web.filters;

import metube.domain.models.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/home")
public class UserHomeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if(req.getMethod().toLowerCase().equals("post") &&
                req.getSession().getAttribute("username") == null){

            resp.sendRedirect("/login");
            return;
        } else if (req.getMethod().equals("get") && req.getSession().getAttribute("username") != null){
            resp.sendRedirect("/home");
            return;
        }

        chain.doFilter(req, resp);

    }
}
