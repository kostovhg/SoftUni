package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

public class DriftRace extends Race {

    public DriftRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    protected int getPerformancePoints(Car car) {
        return car.getSuspension() + car.getDurability();
    }
}
