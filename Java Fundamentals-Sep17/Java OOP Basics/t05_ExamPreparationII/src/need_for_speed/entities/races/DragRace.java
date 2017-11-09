package need_for_speed.entities.races;

import need_for_speed.entities.cars.Car;

public class DragRace extends Race {
    public DragRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    protected int getPerformancePoints(Car car) {
        return car.getHorsePower() / car.getAcceleration();
    }
}
