package products_shop.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    private Gson gson;

    @Autowired
    private FileParser fileParser;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileParser = new FileParser();
    }

    public <T> T importJson(Class<T> clazz, String fileName) throws IOException {
        String file = this.fileParser.readFile(fileName);
        T object = this.gson.fromJson(file, clazz);
        return object;
    }

    public <T> List<T> importJsonList(Class<T> clazz, String fileName) throws IOException {
        Type type = new TypeToken<ArrayList<T>>() {}.getType();
        String file = this.fileParser.readFile(fileName);
        return this.gson.fromJson(file, type);
    }

    public <T> void exportJson(T object, String fileName) throws IOException {
        String content = this.gson.toJson(object);
        this.fileParser.writeFile(fileName, content);
    }
}
