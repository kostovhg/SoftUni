package car_dealer.io;

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
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        this.fileParser = new FileParser();
    }

    public <T> T importJson(Class<T> clazz, String fileName) throws IOException {
        String file = this.fileParser.readFile(fileName);
        T object = this.gson.fromJson(file, clazz);
        return object;
    }

    public <T> List<T> importJson(Iterable<Class<T>> list, String fileName) throws IOException {
        Type type = new TypeToken<ArrayList<T>>() {}.getType();
        return this.gson.fromJson(fileParser.readFile(fileName), type);
    }

    public <T> List<T> importJsonList(Class<T> theClass, String fileName) throws IOException {
        Type listType = new TypeToken<ArrayList<T>>() {}.getType();
        String file = this.fileParser.readFile(fileName);
        return this.gson.fromJson(file, listType);
    }

    public <T> List<T> importJsonList(Type listType, String fileName) throws IOException {
        String file = this.fileParser.readFile(fileName);
        return this.gson.fromJson(file, listType);
    }


    public <T> void exportJson(T object, String fileName) throws IOException {
        String content = this.gson.toJson(object);
        this.fileParser.writeFile(fileName, content);
    }

    public Gson getGson(){
        return this.gson;
    }

    private Type getType(Class<?> clazz) {
        try {
            //Class<?> clazz = Class.forName(typeName);
            TypeToken<?> typeToken = TypeToken.get(clazz);
            return typeToken.getType();
        } catch (Exception e) {
            throw new IllegalArgumentException("Unsupported type: " + clazz.getSimpleName(), e);
        }
    }
}
