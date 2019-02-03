package chushka.web.servlets;

import chushka.domain.models.view.ProductDetailsViewModel;
import chushka.service.ProductService;
import chushka.utils.HtmlReader;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static chushka.utils.Constants.*;

@WebServlet(PRODUCTS_DETAILS_URL)
public class ProductDetailsServlet extends HttpServlet {


    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDetailsViewModel productDetailsViewModel = this.modelMapper
                .map(this.productService.findProductByName(req.getQueryString().split("=")[1]),
                        ProductDetailsViewModel.class);

        String htmlFileContent = this.htmlReader.view(PRODUCT_DETAILS_HTML)
                .replace(PRODUCT_NAME_PATTERN, productDetailsViewModel.getName())
                .replace(PRODUCT_DESCRIPTION_PATTERN, productDetailsViewModel.getDescription())
                .replace(PRODUCT_TYPE_PATTERN, productDetailsViewModel.getType().toUpperCase());

        resp.getWriter().println(htmlFileContent);
    }
}
