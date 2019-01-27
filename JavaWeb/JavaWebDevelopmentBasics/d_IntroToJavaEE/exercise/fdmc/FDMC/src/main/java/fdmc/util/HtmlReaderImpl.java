package fdmc.util;

import java.io.*;


public class HtmlReaderImpl implements HtmlReader {

    @Override
    public String readHtmlFile(String htmlFilePath) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath)
                        )
                )
        );

        StringBuilder htmlFileContent = new StringBuilder();
        String htmlFileLine;

        while ((htmlFileLine = reader.readLine()) != null) {
            htmlFileContent.append(htmlFileLine).append(System.lineSeparator());
        }

        return htmlFileContent.toString().trim();
    }
}
