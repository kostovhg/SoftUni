package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

public class TimeLimitRace extends Race{

    private int goldTime;

    public TimeLimitRace(int length, String route, int prizePool, int goldTime) {
        super(length, route, prizePool);
        this.goldTime = goldTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Car car = super.getParticipants().get(0);
        int timePerformance = getPerformancePoints(car);
        int price = 0;
        String earnedTime = null;
        if(timePerformance <= this.goldTime) {
            price = super.getPrizePool();
            earnedTime = "Gold";
        } else if( timePerformance <= this.goldTime + 15) {
            price = (super.getPrizePool() * 50 ) / 100;
            earnedTime = "Silver";
        } else {
            price = (super.getPrizePool() * 30) / 100;
            earnedTime = "Bronze";
        }
        sb.append(String.format("%s - %d", this.getRoute(), this.getLength())).append(System.lineSeparator());
        sb.append(String.format("%s %s - %d s.",
                car.getBrand(), car.getModel(), timePerformance)).append(System.lineSeparator());
        sb.append(String.format("%s Time, $%d.",
                earnedTime, price));

        return sb.toString();
    }

    @Override
    protected int getPerformancePoints(Car car) {
        return super.getLength()  * ((car.getHorsePower() / 100) * car.getAcceleration());
    }


}
