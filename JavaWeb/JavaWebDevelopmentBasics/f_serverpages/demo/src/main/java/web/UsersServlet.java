package web;

import entities.User;
import services.JsonParser;
import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private final UserService userService;
    private final JsonParser jsonParser;

    @Inject
    public UsersServlet(UserService userService, JsonParser jsonParser){
        this.userService = userService;
        this.jsonParser = jsonParser;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = userService.getAllUsers();

        String usersJson = jsonParser.toJson(users);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(usersJson);
    }
}
