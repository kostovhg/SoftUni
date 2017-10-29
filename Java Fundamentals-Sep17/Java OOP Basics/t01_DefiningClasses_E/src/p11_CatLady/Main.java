package p11_CatLady;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, BaseCat> cats = new LinkedHashMap<>();

        String line;
        while(!(line = reader.readLine()).equals("End")) {

            String[] tokens = line.split("\\s+");

            BaseCat cat = null;
            switch (tokens[0]) {
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(tokens[1], Double.parseDouble(tokens[2]));
                    break;
                case "Cymric":
                    cat = new Cymric(tokens[1], Double.parseDouble(tokens[2]));
                    break;
                case "Siamese":
                    cat = new Siamese(tokens[1], Double.parseDouble(tokens[2]));
                    break;
            }
            cats.put(tokens[1], cat);
        }
        System.out.println(cats.get(reader.readLine()).toString());
    }
}

abstract class BaseCat{
    private String name;

    protected BaseCat(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        //return this.name;
        return String.format("%s %s", this.getClass().getSimpleName(), name);
    }
}

class Siamese extends BaseCat {

    private double earSize;

    public Siamese(String name, double earSize) {
        super(name);
        this.earSize = earSize;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", super.toString(), this.earSize);
    }
}

class Cymric extends BaseCat{
    private double furLength;

    public Cymric(String name, double furLength) {
        super(name);
        this.furLength = furLength;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", super.toString(), this.furLength);
    }
}

class StreetExtraordinaire extends BaseCat{
    private double decibelsOfMewows;

    public StreetExtraordinaire(String name, double decibelsOfMewows) {
        super(name);
        this.decibelsOfMewows = decibelsOfMewows;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", super.toString(), this.decibelsOfMewows);
    }
}
