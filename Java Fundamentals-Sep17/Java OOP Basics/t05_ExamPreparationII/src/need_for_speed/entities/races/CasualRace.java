package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

public class CasualRace extends Race {

    public CasualRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public int getPerformancePoints(Car car){
        return (car.getHorsePower() / car.getAcceleration()) +
                car.getSuspension() + car.getDurability();
    }
}
