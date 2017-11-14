package p03_CarShopExtend;

import p03_CarShopExtend.entities.Audi;
import p03_CarShopExtend.entities.Seat;
import p03_CarShopExtend.interfaces.Car;
import p03_CarShopExtend.interfaces.Rentable;
import p03_CarShopExtend.interfaces.Sellable;

public class Main {
    public static void main(String[] args) {
        Sellable seat = new Seat("Leon", "Gray", 110, "Spain", 11111.1);
        Rentable audi = new Audi("Leon", "Gray", 110, "Spain", 3, 99.9);

        printCarInfo(seat);
        printCarInfo(audi);
    }

    private static void printCarInfo(Car car) {
        System.out.println(String.format(
                "%s is %s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()));
        System.out.println(car.toString());
    }
}
