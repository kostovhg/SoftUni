package helpers.handlers;

import helpers.reader.HttpReader;
import http.HttpRequest;
import http.HttpRequestImpl;

import java.io.IOException;
import java.util.function.Consumer;

public class CookieHandler implements Handler {

    private final Consumer<String> resultConsumer;
    private final HttpReader httpReader;

    public CookieHandler(Consumer<String> resultConsumer, HttpReader httpReader) {
        this.resultConsumer = resultConsumer;
        this.httpReader = httpReader;
    }

    @Override
    public void handle() throws IOException {
        String request = httpReader.readHttpRequest();
        HttpRequest httpRequest = new HttpRequestImpl(request);
        StringBuilder response = new StringBuilder();
        httpRequest.getCookies().forEach(c -> response
                .append(String.format("%s <-> %s", c.getKey(), c.getValue()))
                .append(System.lineSeparator()));
        resultConsumer.accept(response.toString());
    }
}
