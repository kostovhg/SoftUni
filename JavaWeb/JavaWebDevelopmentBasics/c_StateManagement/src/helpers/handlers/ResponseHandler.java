package helpers.handlers;

import helpers.reader.HttpReader;
import http.*;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResponseHandler implements Handler {

    private static final Set<String> RESPONSE_HEADERS = Set.of("Date", "Host", "Content-Type");
    public static final String AUTHORIZATION = "Authorization";

    private final Consumer<String> resultConsumer;
    private final HttpReader httpReader;

    public ResponseHandler(Consumer<String> resultConsumer, HttpReader httpReader) {
        this.resultConsumer = resultConsumer;
        this.httpReader = httpReader;
    }


    private HttpResponse buildResponse(HttpRequest httpRequest, Set<String> validUrls) {
        HttpResponse response = new HttpResponseImpl();

        httpRequest.getHeaders()
                .entrySet()
                .stream()
                .filter(header -> RESPONSE_HEADERS.contains(header.getKey()))
                .forEach(header -> response.addHeader(header.getKey(), header.getValue()));

        if (!validUrls.contains(httpRequest.getRequestUrl())) {
            response.setStatusCode(HttpStatus.NOT_FOUND);
            response.setContent("The requested functionality was not found.".getBytes(HttpConstants.CHARSET));
        } else if (!httpRequest.getHeaders().containsKey(AUTHORIZATION)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setContent("You are not authorized to access the requested functionality".getBytes(HttpConstants.CHARSET));
        } else if ((httpRequest.getMethod().equals("POST")) && httpRequest.getBodyParameters().isEmpty()) {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.setContent("There was an error with the requested functionality due to malformed request.".getBytes(HttpConstants.CHARSET));
        } else {
            response.setStatusCode(HttpStatus.OK);
            String username = decodeAuthorization(httpRequest.getHeaders().get(AUTHORIZATION));
            switch (httpRequest.getMethod()) {
                case "POST":
                    String itemName = httpRequest.getBodyParameters().entrySet().stream().findFirst().orElseThrow().getValue();
                    String itemParts = httpRequest.getBodyParameters().entrySet()
                            .stream()
                            .skip(1)
                            .map(param -> String.format("%s - %s", param.getKey(), param.getValue()))
                            .collect(Collectors.joining(", "));
                    response.setContent(String.format("Greetings %s!, You have successfully created %s with %s.", username, itemName, itemParts)
                            .getBytes(HttpConstants.CHARSET));
                    break;
                case "GET":
                    response.setContent(String.format("Greetings %s", username).getBytes(HttpConstants.CHARSET));
                    break;
                    default:
                        throw new IllegalArgumentException("Unknown or unsupported HTTP method: " + httpRequest.getMethod());
            }
        }

        return response;
    }

    private static String decodeAuthorization(String encoded) {
        if (!encoded.startsWith("Basic ")) {
            throw new IllegalArgumentException("Unknown encoding for string: " + encoded);
        }
        return new String(Base64.getDecoder()
                .decode(encoded.substring("Authorization".length()).getBytes(HttpConstants.CHARSET)), HttpConstants.CHARSET);
    }

    private Set<String> parseUrls(String line) {
        Set<String> urls = new HashSet<>();
        if (line != null) {
            Pattern pattern = Pattern.compile("/[^ ]+");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                urls.add(matcher.group());
            }
        }
        return Collections.unmodifiableSet(urls);
    }

    @Override
    public void handle() throws IOException {
        Set<String> validUrls = parseUrls(httpReader.readLine());
        String request = httpReader.readHttpRequest();
        HttpRequest httpRequest = new HttpRequestImpl(request);
        HttpResponse httpResponse = buildResponse(httpRequest, validUrls);
        resultConsumer.accept(new String(httpResponse.getBytes(), HttpConstants.CHARSET));
    }
}
