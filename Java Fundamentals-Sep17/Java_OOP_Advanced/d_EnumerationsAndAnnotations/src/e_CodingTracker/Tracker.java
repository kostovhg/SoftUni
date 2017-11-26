package e_CodingTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {

    private static Map<String, List<String>> map = new HashMap<>();

    @Author(name = "Pesho")
    static void printMethodsByAuthor(Class<?> cl){
        Method[] arr = cl.getDeclaredMethods();
        //System.out.println(cl.getName());
        for (Method method : arr) {
           Author author = method.getAnnotation(Author.class);
           if(author != null){
               String methodName = method.getName() + "()";
               String authorValue = author.name();
               map.putIfAbsent(authorValue, new ArrayList<>());
               map.get(authorValue).add(methodName);
           }
        }

        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()){
            System.out.println(stringListEntry.getKey() + ": " + String.join(", ", stringListEntry.getValue()));
        }
    }

    @Author(name = "Pesho")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }
}
