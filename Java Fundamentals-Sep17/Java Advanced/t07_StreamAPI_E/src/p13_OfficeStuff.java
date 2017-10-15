import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class p13_OfficeStuff {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static TreeMap<String, LinkedHashMap<String, Integer>> staff = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        int inputCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < inputCount; i++) {
            String[] tokens = reader.readLine().replaceAll("^.|.$", "").split(" - ");
            if(!staff.containsKey(tokens[0])) staff.put(tokens[0], new LinkedHashMap<>());
            if(!staff.get(tokens[0]).containsKey(tokens[2]))
                staff.get(tokens[0]).put(tokens[2], Integer.valueOf(tokens[1]));
            else
                staff.get(tokens[0]).put(tokens[2], staff.get(tokens[0]).get(tokens[2]) + Integer.valueOf(tokens[1]));
        }

        staff.forEach((key, value) -> System.out.printf("%s: %s%n", key,
                value.entrySet().stream().map((prod) -> String.format("%s-%s", prod.getKey(), prod.getValue()))
                        .collect(Collectors.joining(", "))));
    }
}