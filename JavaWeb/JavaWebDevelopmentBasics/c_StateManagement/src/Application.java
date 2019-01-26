import helpers.handlers.CookieHandler;
import helpers.handlers.Handler;
import helpers.reader.HttpReader;
import helpers.reader.HttpReaderImpl;
import http.HttpConstants;
import http.HttpRequest;
import http.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Copied from beautiful solution at
 * https://github.com/Martin-BG/SoftUni-Java-Web-Development-Basics-Jan-2019
 * By MartinBG https://github.com/Martin-BG
 */
public class Application {


    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, HttpConstants.CHARSET))){
            HttpReader httpReader = new HttpReaderImpl(reader);
            Handler handler = new CookieHandler(System.out::println, httpReader);

            handler.handle();
        } catch (IOException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, "ERROR", e);
        }


    }
}
