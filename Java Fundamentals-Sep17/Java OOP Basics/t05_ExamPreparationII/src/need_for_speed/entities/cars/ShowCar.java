package need_for_speed.entities.cars;

public class ShowCar extends Car {

    private int stars;

    public ShowCar(String brand, String model, int yearOfProduction,
            int horsePower, int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsePower, acceleration, suspension, durability);
        this.stars = 0;
    }

    private int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return String.format("%s%d *", super.toString(), this.getStars());
    }

    @Override
    public void tune(int tuneIndex, String addOn) {
        super.tune(tuneIndex, addOn);
        this.stars += tuneIndex;
    }
}
