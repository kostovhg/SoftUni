package chushka.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.stream.Collectors;

import static chushka.utils.Constants.HTML_VIEWS_FILE_PATH_PATTERN;
import static chushka.utils.Constants.NEW_LINE;

public class HtmlReader {

    // Reads absolute paths (bad in case of change environment)
    public String readHtmlFile(String htmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath)
                        )
                )
        );

        StringBuilder htmlFileContent = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            htmlFileContent.append(line).append(NEW_LINE);
        }

        return htmlFileContent.toString().trim();
    }

    // Reads relative paths
    public String view(String viewName) throws IOException {
        var viewPath = MessageFormat.format(HTML_VIEWS_FILE_PATH_PATTERN, viewName);
        URL url = this.getClass().getClassLoader().getResource(viewPath);

        File file = null;
        try {
            assert url != null;
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }

        return Files.lines(Paths.get(file.getAbsolutePath()))
                .collect(Collectors.joining(NEW_LINE));

    }
}
