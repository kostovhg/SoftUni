package lab.b_CarShop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        Car seat = new Seat("Leon", "gray", 110, "Spain");
        System.out.println(String.format(
                "%s is $s color and have %s horse power",
                seat.getModel(),
                seat.getColor(),
                seat.getHorsePower()
        ));
        System.out.println(seat.toString());
    }
}