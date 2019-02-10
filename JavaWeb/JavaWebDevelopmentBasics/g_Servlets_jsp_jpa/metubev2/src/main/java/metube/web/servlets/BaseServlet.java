package metube.web.servlets;

import metube.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(loadOnStartup = 1)
public class BaseServlet extends HttpServlet {

    final UserService userService;
    final ModelMapper modelMapper;

    @Inject
    public BaseServlet(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.modelMapper = mapper;
    }
}
