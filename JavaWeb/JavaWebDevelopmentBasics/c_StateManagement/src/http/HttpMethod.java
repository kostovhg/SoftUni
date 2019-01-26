package http;

import java.util.Locale;

public enum HttpMethod {
    GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH;


    public static HttpMethod get(String methodName) {
        HttpMethod httpMethod = HttpMethod.valueOf(methodName.toUpperCase(Locale.ENGLISH));

        if (httpMethod == null) {
            throw new IllegalArgumentException("Unknown HTTP Method: " + methodName.toUpperCase());
        }

        return httpMethod;
    }
}
