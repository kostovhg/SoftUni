package p08_PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Trainer> trainers = new LinkedHashMap<>();

        String input;
        while(!(input = reader.readLine()).equals("Tournament")){
            String[] tokens = input.split("\\s+");
            if (!trainers.containsKey(tokens[0])) {
                trainers.put(tokens[0], new Trainer(tokens[0]));
            }
            trainers.get(tokens[0]).addPokemon(tokens);
        }
        while(!(input = reader.readLine()).equals("End")) {
            for (Trainer trainer: trainers.values()) {
                if (trainer.haveElement(input)) {
                    trainer.incraceBadges();
                } else {
                    trainer.lowerPokemonsHealth();
                }
            }
        }

        trainers.values().stream()
                .sorted((t1, t2) -> t2.badgesCount() - t1.badgesCount())
                .forEach(t -> System.out.println(t.toString()));
    }
}

class Trainer{
    private String name;
    private int numberOfBadges = 0;
    private Set<Pokemon> pokemonsCollection;

    public Trainer(String name) {
        this.name = name;
        this.pokemonsCollection = new HashSet<>();
    }

    public void addPokemon (String[] tokens) {
        pokemonsCollection.add(new Pokemon(tokens));
    }

    public boolean haveElement(String element) {
        return pokemonsCollection.stream().anyMatch(p -> p.getElement().equals(element));
    }

    public void lowerPokemonsHealth() {
        for (Pokemon pokemon : pokemonsCollection) {
            pokemon.lowerHealth();
        }
        pokemonsCollection.removeIf(p -> p.getHealth() <= 0);
    }

    public void incraceBadges () {
        this.numberOfBadges++;
    }

    public int badgesCount() {
        return numberOfBadges;
    }

    public int pokemonsCount() {
        return pokemonsCollection.size();
    }

    @Override
    public String toString(){
        return String.format(
                "%s %d %d",
                name,
                numberOfBadges,
                pokemonsCollection.size()
        );
    }
}

class Pokemon{

    private String name;
    private String element;
    private int health;

    public Pokemon(String[] tokens) {
        this.name = tokens[1];
        this.element = tokens[2];
        this.health = Integer.parseInt(tokens[3]);
    }

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public void lowerHealth() {
        this.health -= 10;
    }

    public int getHealth() {
        return health;
    }
}
