package fdmc.web.servlets;

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
import static fdmc.util.HtmlPages.*;

@WebServlet(ALL_CATS)
public class AllCatServlet extends HttpServlet {

    private final HtmlRender htmlRender;

    @Inject
    public AllCatServlet(HtmlRender htmlRender) {
        this.htmlRender = htmlRender;
    }

    @Override
    @SuppressWarnings(UNCHECKED)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Map<String, String> cats = ((Map<String, String>)req.getSession().getAttribute(CATS));

        Map<String, String> replacements = new LinkedHashMap<>();
        replacements.put(PAGE, CATS);
        replacements.put(BODY, ALL_CATS_BODY);
        String allCatsPage = htmlRender.render(HTML_TEMPLATE, replacements);
        replacements.clear();
        String result;
        if(cats == null){
            replacements.put(HTML_CONTENT, ALL_CATS_EMPTY);
        } else {
            StringBuilder sb = new StringBuilder();
            for (String catName : cats.keySet()) {
                sb.append(String.format(CAT_PROFILE_LINK, catName));
            }
            replacements.put(HTML_CONTENT, sb.toString());
        }
        result = htmlRender.render(allCatsPage, replacements);

        resp.getWriter().println(result);
    }
}
