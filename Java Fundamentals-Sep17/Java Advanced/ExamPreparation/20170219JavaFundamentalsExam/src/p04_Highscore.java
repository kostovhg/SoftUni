import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_Highscore {

    private static class Match{
        private String oponent;
        private Long points;

        private Match(String op, Long p ){
            this.oponent = op;
            this.points = p;
        }
    }

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static LinkedHashMap<String, ArrayList<Match>> catalogue = new LinkedHashMap<>();
    public static void main(String[] args) {
        String[] input;
        try {
            while(!"osu!".equals((input =
                    reader.readLine().split("\\s+|(<->)"))[0])) {
                Long p1points = Long.valueOf(input[0]);
                String p1 = input[1];
                String p2 = input[2];
                Long p2points = Long.valueOf(input[3]);
                if(!catalogue.containsKey(p1)) {
                    catalogue.put(p1, new ArrayList<>());
                    catalogue.get(p1).add(new Match(" ", 0L));
                }
                if(!catalogue.containsKey(p2)) {
                    catalogue.put(p2, new ArrayList<>());
                    catalogue.get(p2).add(new Match(" ", 0L));
                }
                catalogue.get(p1).add(new Match(p2, p1points - p2points));
                catalogue.get(p1).get(0).points += p1points - p2points;
                catalogue.get(p2).add(new Match(p1, p2points - p1points));
                catalogue.get(p2).get(0).points += p2points - p1points;
            }

            catalogue.entrySet().stream()
                    .sorted((x2, x1) -> x1.getValue().get(0).points.compareTo(x2.getValue().get(0).points))
                    .forEach(p -> {
                        System.out.println(String.format("%s - (%d)",
                                p.getKey(), p.getValue().get(0).points));
                        p.getValue().stream()
                                .filter(o -> !o.oponent.equals(" "))
                                .forEach(o ->
                                System.out.println(String.format("*   %s <-> %d",
                                        o.oponent, o.points)));
                    });
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
