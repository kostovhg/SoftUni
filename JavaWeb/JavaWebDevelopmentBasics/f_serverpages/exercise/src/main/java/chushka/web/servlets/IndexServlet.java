package chushka.web.servlets;

import chushka.domain.models.view.AllProductsViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

import static chushka.utils.Constants.*;

@WebServlet(HOME_URL)
public class IndexServlet extends HttpServlet {


    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper){
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.view(INDEX_HTML);

        htmlFileContent = htmlFileContent.replace(LIST_PRODUCTS_PATTERN, formatListItems());

        resp.getWriter().println(htmlFileContent);
    }

    private String formatListItems(){
        List<AllProductsViewModel> allProducts = this.productService.findAllProducts().stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, AllProductsViewModel.class))
                .collect(Collectors.toList());

        StringBuilder listItems = new StringBuilder();

        allProducts.forEach(product -> listItems.append(String
                .format("<li><a href=\"/products/details?name=%1$s\">%1$s</a>", product.getName())).append(NEW_LINE));

        return listItems.toString().trim();
    }
}
