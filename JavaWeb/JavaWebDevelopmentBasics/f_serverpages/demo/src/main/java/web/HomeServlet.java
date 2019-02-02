package web;

import entities.User;
import services.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public HomeServlet(UserService userService){
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String html = getHtml();

        resp.getWriter().write(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .collect(Collectors.joining());

        User user = new User();

        Arrays.stream(body.split("&"))
                .map(pairString -> pairString.split("="))
                .forEach(pair -> {
                    switch (pair[0]){
                        case "name":
                            user.setName(pair[1].replace('+', ' ').trim());
                            break;
                        case "age":
                            user.setAge(pair[1].trim());
                    }
                });

        userService.add(user);
        String result = getHtml();

        resp.getWriter().write(result);
    }

    public String getUserList() {
        return String.format("<ul>%s</ul>",
                this.userService.getAllUsers().stream()
                        .map(user ->
                                String.format("<li>%s %s</li>", user.getName(), user.getAge())).collect(Collectors.joining(System.lineSeparator())));
    }


    public String getForm() {
        return  "<form action=\"/\" method=\"post\">\n" +
                "    <label for=\"name\">\n" +
                "        <input type=\"text\" id=\"name\" name=\"name\" placeholder=\"Enter your name\" required>\n" +
                "    </label>\n" +
                "<label for=\"age\">\n" +
                "        <input type=\"number\" id=\"age\" name=\"age\" placeholder=\"Enter your age\" required>\n" +
                "    </label>\n" +
                "    <button>Submit</button>\n" +
                "</form>\n";
    }

    public String getHtml() {
        String form = getForm();
        String userList = getUserList();

        return "<!DOCTYPE html>\n" +
                "                <html lang=\"en\">\n" +
                "                <head>\n"  +
                "                    <meta charset=\"UTF-8\">\n" +
                "                    <title>Title</title>\n" +
                "                </head>\n" +
                "                <body>\n"  +
                form + "<br />" +
                userList +
                "</body>\n" +
                "</html>";
    }

}
