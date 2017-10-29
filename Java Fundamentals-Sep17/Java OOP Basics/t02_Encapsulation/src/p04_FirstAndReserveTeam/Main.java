package p04_FirstAndReserveTeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Person> people = new ArrayList<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");
            Person person;
            try {
                person = new Person(
                        input[0],
                        input[1],
                        Integer.parseInt(input[2]),
                        Double.parseDouble(input[3]));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            people.add(person);
        }

        Team team = new Team("Benkovski");
        for (Person person : people) {
            team.addMember(person);
        }
        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");
    }
}
