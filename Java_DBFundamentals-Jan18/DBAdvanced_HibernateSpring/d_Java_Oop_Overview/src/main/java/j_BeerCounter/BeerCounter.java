package j_BeerCounter;

public class BeerCounter {

    public static int beersInStock;
    public static int beersDrankCount;

    public static void BuyBeer(int bottlesCount){
        beersInStock += bottlesCount;
    }

    public static void DrinkBeer(int bottlesCount){
        beersDrankCount += bottlesCount;
        beersInStock -= bottlesCount;
    }
}
