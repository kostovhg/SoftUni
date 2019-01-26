package http;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpConstants {

    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String HTTP_VERSION = "HTTP/1.1";
    public static final String HEADERS_SEPARATOR = ": ";
    public static final String PARAMS_SEPARATOR = "&";
    public static final String PAIR_SEPARATOR = "=";
    public static final String COOKIES_SEPARATOR = "; ";
    public static final String HEADER_COOKIE = "; ";

    private  HttpConstants(){

    }
}
