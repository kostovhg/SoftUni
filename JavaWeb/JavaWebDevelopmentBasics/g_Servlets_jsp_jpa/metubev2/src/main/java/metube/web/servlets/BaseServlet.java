package metube.web.servlets;

import metube.service.UserService;
import metube.utils.Mapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = 1)
public class BaseServlet extends HttpServlet {

    final UserService userService;
    final Mapper mapper;

//    @Inject
    public BaseServlet(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }
}
