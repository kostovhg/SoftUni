import java.util.*;

public class p04_Highscore {

    private static Map<String, Long> scores = new LinkedHashMap<>();
    private static Map<String, List<String>> players = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String regex = "<->| ";

    public static void main(String[] args) {
        String line;
        while(!"osu!".equals(line = scanner.nextLine())){
            String[] tokens = line.split(regex);
            String p1 = tokens[1];
            String p2 = tokens[2];
            Long p1_p2 = Long.valueOf(tokens[0]) - Long.valueOf(tokens[3]);
            if(!scores.containsKey(p1)){
                scores.put(p1,0L);
                players.put(p1, new ArrayList<>());
            }
            if(!scores.containsKey(p2)){
                scores.put(p2, 0L);
                players.put(p2, new ArrayList<>());
            }
            scores.compute(p1, (k, v) -> v += p1_p2);
            scores.compute(p2, (k, v) -> v -= p1_p2);
            players.get(p1).add("*   " + p2 + " <-> " + p1_p2);
            players.get(p2).add("*   " + p1 + " <-> " + -p1_p2);
        }
        StringBuilder output = new StringBuilder();
        scores.entrySet().stream()
                .sorted((p2, p1) -> Long.compare(p1.getValue(), p2.getValue()))
                .forEach(p -> {
                    output.append(p.getKey()).append(" - (").append(p.getValue()).append(")\n");
                    players.get(p.getKey()).stream()
                            .forEach(x -> output.append(x).append("\n"));
                });
        System.out.println(output);
    }
}
