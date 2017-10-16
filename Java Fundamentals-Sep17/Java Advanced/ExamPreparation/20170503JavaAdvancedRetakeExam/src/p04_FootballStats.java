import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_FootballStats {
    public static void main(String[] args) throws IOException{
        HashMap<String, List<String[]>> stats = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!(line = reader.readLine()).equalsIgnoreCase("Season End")){
            String[] tokens = line.split("[\\-\\s:]+");
            String firstTeam = tokens[0];
            String secondTeam = tokens[1];
            String firstTeamGoals = tokens[3];
            String secondTeamGoals = tokens[4];

            if(!stats.containsKey(firstTeam)){
                stats.put(firstTeam, new ArrayList<>());
            }
            if(!stats.containsKey(secondTeam)){
                stats.put(secondTeam, new ArrayList<>());
            }
            stats.get(firstTeam)
                    .add(new String[]{secondTeam, String.format("%s:%s", firstTeamGoals, secondTeamGoals)});
            stats.get(secondTeam)
                    .add(new String[]{firstTeam, String.format("%s:%s", secondTeamGoals, firstTeamGoals)});
        }

        List<String> teams = new ArrayList<>();
        teams.addAll(Arrays.asList(reader.readLine().split(", ")));

        teams.stream().forEach(t ->
                // t is teams as they are ordered in the request array "teams"
                stats.get(t).stream()
                        .sorted(Comparator.comparing(x -> x[0]))
                        .forEach(x -> System.out.printf("%s - %s -> %s%n", t, x[0], x[1])));
    }
}
