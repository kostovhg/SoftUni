import java.util.*;

public class p04_FootballStats {

    private static HashMap<String, List<String>> stats = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String splitter = "( - )|( )|(:)|(, )";

    public static void main(String[] args) {
        String line;
        while(!"Season End".equals(line = scanner.nextLine())){
            String[] tokens = line.split(splitter);
            String firstTeam = tokens[0];
            String secondTeam = tokens[1];
            String firstTeamGoals = tokens[3];
            String secondTeamGoals = tokens[4];

            stats.putIfAbsent(firstTeam, new ArrayList<>());
            stats.putIfAbsent(secondTeam, new ArrayList<>());
            stats.get(firstTeam).add(secondTeam + " -> " + firstTeamGoals + ":" + secondTeamGoals);
            stats.get(secondTeam).add(firstTeam + " -> " + secondTeamGoals + ":" + firstTeamGoals);
        }
        for (String team : scanner.nextLine().split(splitter)) {
            stats.get(team).stream()
                    .sorted(Comparator.comparing(x -> x.substring(0, x.indexOf(" "))))
                    .forEach(o -> System.out.printf("%s - %s%n", team, o));
        }
    }
}
