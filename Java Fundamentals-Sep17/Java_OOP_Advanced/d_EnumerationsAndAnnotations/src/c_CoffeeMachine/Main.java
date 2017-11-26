package c_CoffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CoffeeMachine machine = new CoffeeMachine();
        String input = null;
        while(!(input = reader.readLine()).equals("END")){
            String[] tokens = input.split("\\s+");
            if(tokens.length > 1){
                machine.buyCoffee(tokens[0], tokens[1]);
            } else {
                machine.insertCoin(tokens[0]);
            }
        }

        for(Coffee coffee : machine.coffeesSold()){
            System.out.println(coffee);
        }
    }
}