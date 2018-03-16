package lab.c_CarShopExtend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Sellable seat = new Seat("Leon", "gray", 110, "Spain", 11111.1);
        Rentable audi = new Audi("Leon", "gray", 110, "Spain", 3, 99.9);

        printCarInfo(seat);
        printCarInfo(audi);
    }

    private static void printCarInfo(Car car) {

        System.out.println(String.format(
                "%s is $s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()
        ));

        System.out.println(car.toString());
    }
}