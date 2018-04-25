package xmlProcessingE.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xmlProcessingE.domain.dto.DTOConvertUtil;
import xmlProcessingE.domain.dto.binding.categories.CategoriesWrapperModel;
import xmlProcessingE.domain.dto.binding.product.ProductsWrapperModel;
import xmlProcessingE.domain.dto.binding.user.UsersWrapperModel;
import xmlProcessingE.domain.dto.view.products.ProductsInRangeWrapper;
import xmlProcessingE.domain.dto.view.users.UserModel;
import xmlProcessingE.domain.dto.view.users.UsersSoldProductsWrapper;
import xmlProcessingE.domain.model.Product;
import xmlProcessingE.domain.model.User;
import xmlProcessingE.io.XmlParser;
import xmlProcessingE.service.CategoryService;
import xmlProcessingE.service.ProductService;
import xmlProcessingE.service.UserService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    public static final String INPUT_FILES_FOLDER = System.getProperty("user.dir") + "/src/main/resources/files/input";
    public static final String OUTPUT_FILES_FOLDER = "./src/main/resources/files/output";
    public static final String USERS_XML_FILE_LOCATION = INPUT_FILES_FOLDER + "/xml/users.xml";
    public static final String CATEGORIES_XML_FILE_LOCATION = INPUT_FILES_FOLDER + "/xml/categories.xml";
    public static final String PRODUCTS_XML_FILE_LOCATION = INPUT_FILES_FOLDER + "/xml/products.xml";
    public static final String PRODUCTS_IN_RANGE_OUTPUT_XML_FILE = OUTPUT_FILES_FOLDER + "/xml/products-in-range.xml";
    public static final String PRODUCTS_SUCCESSFULLY_SOLD_OUTPUT_XML_FILE = OUTPUT_FILES_FOLDER + "/xml/users-sold-products.xml";


    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;
    private XmlParser parser;

    @Autowired
    public Terminal(UserService userService, ProductService productService, CategoryService categoryService, XmlParser parser) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.parser = parser;
    }

    @Override
    public void run(String... args) throws Exception {

        // seedDatabase();

        // exportAllProductsInPriceRange("500", "1000");

        exportSuccessfullySoldProducts();

    }

    private void exportSuccessfullySoldProducts() {
        List<UserModel> usersSoldProducts = this.userService.findAllBySoldProducts();
        UsersSoldProductsWrapper result = new UsersSoldProductsWrapper();
        result.setUsers(usersSoldProducts);

        try {
            this.parser.writeXML(result , PRODUCTS_SUCCESSFULLY_SOLD_OUTPUT_XML_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void exportAllProductsInPriceRange(String lBound, String uBound) {
        List<Product> allByRangeWithoutBuyer = this.productService.getAllByRangeWithoutBuyer(new BigDecimal(lBound), new BigDecimal(uBound));
        ProductsInRangeWrapper allProductsWrapper = new ProductsInRangeWrapper();
        //allProductsWrapper.setProducts(DTOConvertUtil.convert(allByRangeWithoutBuyer, ProductsInRangeModel.class));
        allProductsWrapper.setProducts(DTOConvertUtil.convert(allByRangeWithoutBuyer));
        try {
            this.parser.writeXML(allProductsWrapper, PRODUCTS_IN_RANGE_OUTPUT_XML_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void seedDatabase() {

        UsersWrapperModel userWrapperDto = parser
                .deserializeXml(USERS_XML_FILE_LOCATION, UsersWrapperModel.class);
        this.userService.saveAll(userWrapperDto.getUsers());

        CategoriesWrapperModel categoriesWrapperModel = parser
                .deserializeXml(CATEGORIES_XML_FILE_LOCATION, CategoriesWrapperModel.class);
        this.categoryService.saveAll(categoriesWrapperModel.getCategories());

        ProductsWrapperModel productsWrapperModel = parser
                .deserializeXml(PRODUCTS_XML_FILE_LOCATION, ProductsWrapperModel.class);
        this.productService.saveAll(productsWrapperModel.getProducts(), this.userService, this.categoryService);

        //System.out.println("test");
    }
}
