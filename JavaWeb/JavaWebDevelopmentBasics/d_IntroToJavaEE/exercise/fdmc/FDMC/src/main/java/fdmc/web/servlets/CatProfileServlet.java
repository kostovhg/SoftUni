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
import static fdmc.util.HtmlPages.*;

@WebServlet(CATS_PROFILE)
public class CatProfileServlet extends HttpServlet {

    //private final HtmlReader htmlReader;
    private final HtmlRender htmlRender;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader, HtmlRender htmlRender) {
        //this.htmlReader = htmlReader;
        this.htmlRender = htmlRender;
    }

    @Override
    @SuppressWarnings(UNCHECKED)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: on empty session map, redirect to error page
        String catName;
        Cat cat = null;
        if(req.getSession().getAttribute(CATS) == null){
            catName = " ";
        } else {
            catName = req.getQueryString().split(PARAM_SEPARATOR_REGEX)[1];
            cat = ((Map<String, Cat>) req.getSession().getAttribute(CATS))
                    .get(catName);
        }

        Map<String, String> replacements = new LinkedHashMap<>();

        String result;
        if (cat == null){
            replacements.put(PAGE, ERROR);
            replacements.put(BODY, NOT_EXISTENT_BODY);
            String errorPage = htmlRender.render(HTML_TEMPLATE, replacements);
            replacements.put(NAME, catName);
            result = htmlRender.render(errorPage, replacements);
        } else {
            replacements.put(PAGE, catName);
            replacements.put(BODY, PROFILE_BODY);
            String profilePage = htmlRender.render(HTML_TEMPLATE, replacements);
            replacements.put(NAME, cat.getName());
            replacements.put(BREED, cat.getBreed());
            replacements.put(COLOR, cat.getColor());
            replacements.put("legs", cat.getNumberOfLegs().toString());
            result = htmlRender.render(profilePage, replacements);
        }

        resp.getWriter().println(result);

//        String htmlFileContent = this.htmlReader
//                .readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
//                .replace("{{name}}", cat.getName())
//                .replace("{{color}}", cat.getColor())
//                .replace("{{breed}}", cat.getBreed())
//                .replace("{{legs}}", cat.getNumberOfLegs().toString());
//
//
//        resp.getWriter().println(htmlFileContent);
    }
}
