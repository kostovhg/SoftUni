package services.implementations;

import com.google.gson.Gson;
import services.JsonParser;

public class GsonJsonParser implements JsonParser {
    @Override
    public String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
