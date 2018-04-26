package org.softuni.ruk.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.ruk.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private final Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        return this.gson.fromJson(fileContent, objectClass);
    }

    @Override
    public <T> String write(T object) throws IOException {
        return this.gson.toJson(object);
    }
}
