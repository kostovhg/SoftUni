import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p04_AshesOfRoses {
    private static Pattern pattern = Pattern.compile("^Grow <(?<reg>[A-Z][a-z]*)> <(?<col>[a-zA-Z0-9]+)> (?<am>\\d+)$");
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, Map<String, Long>> regions = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        String line;
        while(!"Icarus, Ignite!".equals(line = reader.readLine())){
            Matcher m = pattern.matcher(line);
            if(!m.find()) continue;
            String region = m.group("reg");
            String color = m.group("col");
            Long amout = Long.valueOf(m.group("am"));
            if(!regions.containsKey(region)){
                regions.put(region, new TreeMap<>());
                regions.get(region).put("*", 0L);
            }
            if(!regions.get(region).containsKey(color)){
                regions.get(region).put(color, 0L);
            }
            regions.get(region)
                    .compute("*", (k, v) -> v + amout);
            regions.get(region)
                    .compute(color, (k, v) -> v + amout);

        }

        regions.entrySet().stream().sorted((r1, r2) -> {
            if(Long.compare(r1.getValue().get("*"), r2.getValue().get("*")) == 0)
                return r1.getKey().compareTo(r2.getKey());
            return Long.compare(r2.getValue().get("*"), r1.getValue().get("*"));
        }).forEach(r -> {
            System.out.println(r.getKey());
            r.getValue().entrySet().stream().filter(c -> !c.getKey().equals("*"))
                    .sorted((c1, c2) -> {
                if(Long.compare(c1.getValue(), c2.getValue()) == 0)
                    return c1.getKey().compareTo(c2.getKey());
                return Long.compare(c1.getValue(), c2.getValue());
                    }).forEach(c -> System.out.println("*--" + c.getKey() +" | " + c.getValue()));
        });
    }
}
