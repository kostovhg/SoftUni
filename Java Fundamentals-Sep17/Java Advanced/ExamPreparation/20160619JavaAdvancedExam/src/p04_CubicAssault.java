import java.util.*;

public class p04_CubicAssault {

    private static final Integer top = 1000000;

    private static Map<String, HashMap<String, Long>> statistics = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String regex = " -> ";
        String line;
        while(!"Count em all".equals(line = scanner.nextLine())){
            String[] tokens = line.split(regex);
            String name = tokens[0];
            String type = tokens[1];
            Long count = Long.valueOf(tokens[2]);

            statistics.putIfAbsent(name, new HashMap<String, Long>(){{
            put("Black", 0L);
            put("Red", 0L);
            put("Green", 0L);}});

            HashMap<String, Long> current = statistics.get(name);
            current.put(type, current.get(type) + count);
            Long green = current.get("Green");
            Long red = current.get("Red");
            Long black = current.get("Black");
            if(green >= top){
                red += green / top;
                green %= top;
            }
            if(red >= top){
                black += red / top;
                red %= top;
            }
            current.put("Black", black);
            current.put("Red", red);
            current.put("Green", green);
        }

        StringBuilder output = new StringBuilder();

        statistics.entrySet().stream()
                .sorted((x2, x1) -> {
                    if(Objects.equals(x1.getValue().get("Black"), x2.getValue().get("Black"))){
                        if(x2.getKey().length() - x1.getKey().length() == 0)
                            return x2.getKey().compareTo(x1.getKey());
                        else return x2.getKey().length() - x1.getKey().length();
                    } else {
                        return Long.compare(x1.getValue().get("Black"), x2.getValue().get("Black"));
                    }
                }).forEach(x -> {
            output.append(x.getKey()).append("\n");
            x.getValue().entrySet().stream()
                    .sorted((m2, m1) -> {
                        if(Objects.equals(m2.getValue(), m1.getValue())) return m2.getKey().compareTo(m1.getKey());
                        else return Long.compare(m1.getValue(), m2.getValue());
                    }).forEach(m ->
                            output.append(String.format("-> %s : %d\n", m.getKey(), m.getValue())));

        });
        System.out.println(output);
    }
}
