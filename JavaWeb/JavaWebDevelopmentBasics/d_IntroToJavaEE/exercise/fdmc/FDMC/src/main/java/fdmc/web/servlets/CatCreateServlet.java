package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;
import fdmc.util.HtmlRender;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static fdmc.util.Constants.*;
import static fdmc.util.HtmlPages.CREATE_BODY;
import static fdmc.util.HtmlPages.HTML_TEMPLATE;

@WebServlet(CATS_CREATE)
public class CatCreateServlet extends HttpServlet {

    //private final HtmlReader htmlReader;
    private final HtmlRender htmlRender;

    @Inject
    public CatCreateServlet(HtmlReader htmlReader, HtmlRender htmlRender) {
        //this.htmlReader = htmlReader;
        this.htmlRender = htmlRender;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //        resp.getWriter().println(this.htmlReader.readHtmlFile(CAT_CREATE_HTML));

        Map<String, String> replacements = new LinkedHashMap<>();
        replacements.put(PAGE, CREATE);
        replacements.put(BODY, CREATE_BODY);

        resp.getWriter().println(htmlRender.render(HTML_TEMPLATE, replacements));

    }

    @Override
    @SuppressWarnings(UNCHECKED)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cat cat = new Cat();

        if(req.getParameter(NAME).isEmpty() || req.getParameter(NAME).isBlank()) {
            resp.sendRedirect(String.format(CATS_PROFILE_URL_PATTERN, "noname"));
        } else {
            cat.setName(req.getParameter(NAME));
            cat.setBreed(req.getParameter(BREED));
            cat.setColor(req.getParameter(COLOR));
            cat.setNumberOfLegs(Integer.valueOf(req.getParameter(LEGS)));

            if (req.getSession().getAttribute(CATS) == null) {
                req.getSession().setAttribute(CATS, new LinkedHashMap<String, Cat>());
            }

            ((Map<String, Cat>) req.getSession().getAttribute(CATS)).putIfAbsent(cat.getName(), cat);

            resp.sendRedirect(String.format(CATS_PROFILE_URL_PATTERN, cat.getName()));
        }
    }
}
