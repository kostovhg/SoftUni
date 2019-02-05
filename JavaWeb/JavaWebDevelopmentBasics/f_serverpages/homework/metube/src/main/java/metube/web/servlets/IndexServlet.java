package metube.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static metube.utils.Constants.INDEX_JSP;
import static metube.utils.Constants.INDEX_URL;
import static metube.utils.JspFileNameBuilder.getJsp;

@WebServlet(INDEX_URL)
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getJsp(INDEX_JSP)).forward(req, resp);
    }
}
