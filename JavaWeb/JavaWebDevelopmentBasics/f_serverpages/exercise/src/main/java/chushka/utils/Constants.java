package chushka.utils;

public class Constants {


    public static final String NEW_LINE = System.lineSeparator();

    public static final String NAME_PROPERTY = "name";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String TYPE_PROPERTY = "type";

    // URLs
    public static final String HOME_URL = "/";
    public static final String PRODUCTS_CREATE_URL = "/products/create";
    public static final String PRODUCTS_DETAILS_URL = "/products/details";

    // Html files names
    public static final String INDEX_HTML = "index";
    public static final String CREATE_PRODUCT_HTML = "create-product";
    public static final String PRODUCT_DETAILS_HTML = "details-product";

    // patterns
    public static final String HTML_VIEWS_FILE_PATH_PATTERN = "views/{0}.html";
    public static final String TYPE_OPTIONS_PATTERN = "{{typeOptions}}";
    public static final String LIST_PRODUCTS_PATTERN = "{{listProducts}}";
    public static final String PRODUCT_NAME_PATTERN = "{{productName}}";
    public static final String PRODUCT_DESCRIPTION_PATTERN = "{{productDescription}}";
    public static final String PRODUCT_TYPE_PATTERN = "{{productType}}";
}
