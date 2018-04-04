package core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class Config {

    public static void main(String[] args) throws IOException {

        InputStream input = Config.class.getClassLoader().getResourceAsStream("params.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(input));
        System.out.println(properties.getProperty("property"));
        System.out.println(properties.getProperty("another.property"));

    }
}
