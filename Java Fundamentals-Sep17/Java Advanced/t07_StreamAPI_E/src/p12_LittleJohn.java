import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class p12_LittleJohn {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, Integer> arrows = new LinkedHashMap<String, Integer>(){{
            put(">>>----->>", 0); put(">>----->", 0); put(">----->", 0);}};

        for (int i = 0; i < 4; i++) {
            String line = reader.readLine();
            // if(line.length() < 7) continue;
            for (Map.Entry<String, Integer> arr :
                    arrows.entrySet()) {
                int index = line.indexOf(arr.getKey());
                while(index > -1){
                    arrows.put(arr.getKey(), arr.getValue() + 1);
                    index = line.indexOf(arr.getKey(), index + arr.getKey().length());
                }
                // replace with neutral symbol, to ensure that after replacement we will not produce new arrow
                // -> nested arrows
                line = line.replaceAll(arr.getKey(), "*");
            }
        }
        long num = Long.parseLong(arrows.entrySet().stream()
            .sorted(Comparator.comparingInt(arr -> (arr.getKey().length())))
            .map(e -> e.getValue().toString()).collect(Collectors.joining("")));
        StringBuilder strNum = new StringBuilder(Long.toString(num, 2));
        strNum.append(new StringBuilder(strNum).reverse());
        System.out.println(Long.parseLong(strNum.toString(), 2));
    }
}