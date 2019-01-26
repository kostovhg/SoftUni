package http;

import java.util.*;
import java.util.regex.Pattern;

public class HttpRequestImpl implements HttpRequest {

    private static final Pattern PAIR_SPLIT_PATTERN = Pattern.compile(HttpConstants.PAIR_SEPARATOR);

    private final Map<String, String> headers;
    private final Map<String, String> bodyParameters;
    private final List<HttpCookie> cookies;
    private HttpMethod method;
    private String requestUrl;

    public HttpRequestImpl(String input) {
        // Initialize collections
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new ArrayList<>();

        // Split input on different components groups
        String[] headAndBody = input.trim().split(System.lineSeparator() + System.lineSeparator());
        String[] head = headAndBody[0].split(System.lineSeparator());
        String[] firstLine = head[0].split("\\s+");

        // Set method and requestUrl
        this.setMethod(firstLine[0]);
        this.setRequestUrl(firstLine[1]);

        // Iterate all except first line of input headers
        for (int i = 1; i < head.length; i++) {
            String[] kvp = head[i].split(": ");
            if(kvp.length != 2) continue;
            // proceed cookies
            if(kvp[0].equals("Cookie")){
                this.addCookies(kvp[1]);
            } else {
                this.addHeader(kvp[0], kvp[1]);
            }
        }

        // If there is a body iterate all key value pairs to fill bodyParameters
        if(headAndBody.length > 1 && headAndBody[1].contains("=")){
            String[] pairs = headAndBody[1].split("&");
            for (String pair : pairs) {
                String[] kvp = pair.split("=");
                if(kvp.length != 2) continue;
                this.addBodyParameter(kvp[0], kvp[1]);
            }
        }
    }


    private void addCookies(String parameters){
        Arrays.stream(parameters.split(HttpConstants.COOKIES_SEPARATOR))
                .map(PAIR_SPLIT_PATTERN::split)
                .forEach(kvp -> addCookie(kvp[0], kvp[1]));
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return Collections.unmodifiableMap(this.bodyParameters);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return Collections.unmodifiableList(this.cookies);
    }

    @Override
    public String getMethod() {
        return method.toString();
    }

    @Override
    public void setMethod(String method) {
        this.method = HttpMethod.valueOf(method);
    }

    @Override
    public String getRequestUrl() {
        return requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.putIfAbsent(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.putIfAbsent(parameter, value);
    }

    @Override
    public void addCookie(String key, String value) {
        this.cookies.add(new HttpCookie(key, value));
    }

    @Override
    public boolean isResource() {
        return this.requestUrl.contains(".");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result
                .append(String.format("%s %s %s", this.getMethod(), this.getRequestUrl(), HttpConstants.HTTP_VERSION))
                .append(System.lineSeparator());
        for (String header :
                this.headers.keySet()) {
            result.append(header).append(": ").append(this.headers.get(header)).append(System.lineSeparator());
        }
        result.append(System.lineSeparator());

        for (Object bodyParameter : this.bodyParameters.keySet()) {
            result.append(bodyParameter).append(" -> ").append(this.bodyParameters.get(bodyParameter)).append(System.lineSeparator());
        }

        return result.toString();
    }
}
