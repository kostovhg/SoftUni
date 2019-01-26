package http;

import java.util.*;

public class HttpResponseImpl implements HttpResponse {

    private final Map<String, String> headers;
    private byte[] content;
    private HttpStatus httpStatus;

    public HttpResponseImpl() {
        this.content = new byte[0];
        this.headers = new LinkedHashMap<>();

    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.httpStatus;
    }

    @Override
    public byte[] getContent() {
        return this.toString().getBytes();
    }

    @Override
    public byte[] getBytes() {
        return this.content;
    }

    @Override
    public void setStatusCode(HttpStatus statusCode) {
        this.httpStatus = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content.clone();
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result
                .append(String.format("HTTP/1.1 %d %s", this.httpStatus.getCode(), this.httpStatus.getName()))
                .append(System.lineSeparator());
        for (String key : Arrays.asList("Date", "Host", "Content-Type")) {
            if (this.headers.containsKey(key)) {
                result.append(String.format("%s: %s%n", key, this.headers.get(key)));
            }
        }

        result.append(System.lineSeparator());

        return result.toString();
    }
}
