package metube.web.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if (req.getSession().getAttribute("username") != null){
//            resp.sendRedirect("/home");
//            return;
//        }

        req.getRequestDispatcher("/jsp/index.jsp").forward(req,resp);
    }
}
