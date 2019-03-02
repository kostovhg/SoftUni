package realestateagency.utils;

import java.math.BigDecimal;

public final class Constants {

    public static final String DEFAULT_DECIMAL_ZERO_DEFINITION = "DECIMAL(10, 2) DEFAULT '0.00'";
    public static final BigDecimal BIG_DECIMAL_HUNDRED = new BigDecimal("100");
    public static final String INDEX_SRC_ABSOLUTE_PATH = System.getProperty("user.dir") + "/src/main/resources/static/index.html";
    public static final String INDEX_TARGET_RELATIVE_PATH = "target/classes/static/index.html";
    public static final String HTML_PLACEHOLDER = "{{offers}}";
    public static final String EMPTY_APARTMENT_DIV =
            "<div class=\"apartment\" style=\"border: red solid 1px\">\n" +
            "<p>There aren't any offers</p>\n" +
            "</div>";
    public static final String FORMAT_OFFER_DIV = "<div class=\"apartment\">\n" +
            "<p>Rent: %.2f</p>\n" +
            "<p>Type: %s</p>\n" +
            "<p>Commission: %.2f%%</p>\n" +
            "</div>\n";
}
