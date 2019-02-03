package chushka.web.servlets;

import chushka.domain.entities.ProductType;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.ProductService;
import chushka.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static chushka.utils.Constants.*;

@WebServlet(PRODUCTS_CREATE_URL)
public class ProductCreateServlet extends HttpServlet {



    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader){
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.view(CREATE_PRODUCT_HTML);

        htmlFileContent = htmlFileContent.replace(TYPE_OPTIONS_PATTERN, formatTypeOptions());

        resp.getWriter().println(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter(NAME_PROPERTY));
        productServiceModel.setDescription(req.getParameter(DESCRIPTION_PROPERTY));
        productServiceModel.setType(req.getParameter(TYPE_PROPERTY));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect("/");
    }

    private String formatTypeOptions(){
        StringBuilder options = new StringBuilder();

        Arrays.stream(ProductType.values()).forEach(type -> options.append(String.format("<option value=\"%1$s\">%1$s</option>", type.getType())).append(NEW_LINE));

        return options.toString().trim();
    }
}
