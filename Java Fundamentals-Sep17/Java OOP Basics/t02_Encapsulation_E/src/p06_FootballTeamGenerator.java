import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyException;
import java.util.*;

public class p06_FootballTeamGenerator {
    private static Map<String, Team> teams = new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(!(input = reader.readLine()).equalsIgnoreCase("END")){
            int indexOfSemicolon = input.indexOf(";");
            String command = input.substring(0, indexOfSemicolon);
            String rest = input.substring(input.substring(0, indexOfSemicolon + 1).indexOf(";") +1);

            if(rest.indexOf(";") != -1){
                String team = rest.substring(0, rest.indexOf(";"));
                String[] tokens = rest.substring(rest.indexOf(";")+1).split(";");
                if(!teams.containsKey(team) && !command.equalsIgnoreCase("team")) {
                    System.out.println(Exceptions.invalid_team(team));
                    continue;
                }
                Team cTeam = teams.get(team);
                if(command.equalsIgnoreCase("add")){
                    try {
                        cTeam.addPlayer(tokens[0], tokens);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (command.equalsIgnoreCase("remove")) {
                    try {
                        cTeam.removePlayer(tokens[0]);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                if(command.equalsIgnoreCase("team")){
                    try {
                        teams.put(rest, new Team(rest));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        System.out.println(teams.get(rest).toString());
                    } catch (NullPointerException e) {
                        System.out.println("Team " + rest + " does not exist.");
                    }
                }

            }
        }
    }
}

class Player {
    private static final String[] STATS = {"Endurance", "Sprint", "Dribble", "Passing", "Shooting"};

    private String name;
    private int[] skills = new int[5];

    public Player(String[] args) {
        setName(args[0]);
        for (int i = 1; i < args.length; i++) {
            int skill;
            try {
                skill = Integer.parseInt(args[i]);
            } catch (Exception e){
                throw new IllegalArgumentException(Exceptions.invalid_stat(STATS[i - 1]));
            }
            if (skill < 0 || skill > 100) {
                throw new IllegalArgumentException(Exceptions.invalid_stat(STATS[i - 1]));
            }
            skills[i - 1] = Integer.parseInt(args[i]);
        }
    }

    private void setName(String n) {
        if (n.isEmpty() || n.trim().isEmpty()) {
            throw new IllformedLocaleException(Exceptions.EMPTY_NAME);
        }
        this.name = n;
    }

    public double getOverallSkill() {
        double average = 0;
        for (int skill : skills) {
            average += skill;
        }
        return average / skills.length;
    }
}

class Team {
    private String name;
    private Map<String, Player> players;

    public Team(String name) {
        setName(name);
        players = new LinkedHashMap<>();
    }

    private void setName(String n) {
        if (n.isEmpty() || n.trim().isEmpty() || n.equals("") || n.equals(null)) {
            throw new IllegalArgumentException(Exceptions.EMPTY_NAME);
        }
        this.name = n;
    }

    public double getRating() {
        if (players.isEmpty()) return 0.0;
        return players.values().stream()
                .mapToDouble(Player::getOverallSkill)
                .average().getAsDouble();
    }

    public void addPlayer(String name, String[] args) {
        players.put(name, new Player(args));
    }

    public void removePlayer(String name) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException("Player " + name + " is not in " + this.name + " team.");
        }
        players.remove(name);
    }

    @Override
    public String toString(){
        return String.format("%s - %d", name, Math.round(getRating()));
    }
}

class Exceptions {
    public static final String EMPTY_NAME = "A name should not be empty.";

    public static String invalid_stat(String stat) {
        return stat + " should be between 0 and 100.";
    }

    public static String missing_player(String p, String t) {
        return "Player " + p + " is not in " + t + " team.";
    }

    public static String invalid_team(String t) {
        return "Team " + t + " does not exist.";
    }
}
