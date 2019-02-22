package casebook.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({
        "", "/index", "/register", "/login"
})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if( httpServletRequest.getSession().getAttribute("username") != null) {
            httpServletResponse.sendRedirect("/home");
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
