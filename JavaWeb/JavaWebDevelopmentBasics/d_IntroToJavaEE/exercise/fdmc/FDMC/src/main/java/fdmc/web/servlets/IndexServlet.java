package fdmc.web.servlets;

import fdmc.util.HtmlRender;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static fdmc.util.Constants.*;
import static fdmc.util.HtmlPages.HOME_BODY;
import static fdmc.util.HtmlPages.HTML_TEMPLATE;

@WebServlet(ROOT_URL)
public class IndexServlet extends HttpServlet {

    //private final HtmlReader htmlReader;
    private final HtmlRender htmlRender;

    @Inject
    public IndexServlet(HtmlReader htmlReader, HtmlRender htmlRender) {
        //this.htmlReader = htmlReader;
        this.htmlRender = htmlRender;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();
        Map<String, String> replacements = new LinkedHashMap<>();
        replacements.put(PAGE, HOME);
        replacements.put(BODY, HOME_BODY);

//        writer.println(this.htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH));

//        String string = HTML_TEMPLATE
//                .replace("{{page}}", "home")
//                .replace("{{body}}", HOME_BODY);
//        writer.println(string);

        writer.println(htmlRender.render(HTML_TEMPLATE, replacements));
    }

}
