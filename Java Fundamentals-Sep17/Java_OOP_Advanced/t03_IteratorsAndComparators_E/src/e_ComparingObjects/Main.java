package e_ComparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<Person> persons = new ArrayList<>();
        String input = null;

        while(!(input = reader.readLine()).equals("END")){
            String[] personData = input.split("\\s+");
                persons.add(new Person(personData));
        }

        Person choosedPerson = persons.get(Integer.valueOf(reader.readLine()) - 1);

        final int[] numberOfComparedPeople = {0, 0};
        persons.forEach(
                p -> {
                    if(choosedPerson.compareTo(p) == 0){
                        numberOfComparedPeople[0]++;
                    } else {
                        numberOfComparedPeople[1]++;
                    }
                }
        );
        if(numberOfComparedPeople[0] < 2){
            System.out.println("No matches");
            return;
        }
        System.out.printf(
                "%d %d %d%n",
                numberOfComparedPeople[0],
                numberOfComparedPeople[1],
                persons.size());
    }
}