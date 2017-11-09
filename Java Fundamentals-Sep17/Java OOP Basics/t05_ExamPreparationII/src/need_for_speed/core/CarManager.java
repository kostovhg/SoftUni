package need_for_speed.core;

import need_for_speed.entities.cars.Car;
import need_for_speed.entities.garage.Garage;
import need_for_speed.entities.races.Race;
import need_for_speed.factories.CarFactory;
import need_for_speed.factories.RaceFactory;

import java.util.LinkedHashMap;
import java.util.Map;

import static need_for_speed.utilities.Constants.*;

public class CarManager {
    private final Map<Integer, Car> cars;
    private final Map<Integer, Race> races;
    private final Garage garage;


    public CarManager() {
        this.cars = new LinkedHashMap<>();
        this.races = new LinkedHashMap<>();
        this.garage = new Garage();
    }

    public void register(int id, String type, String brand, String model,
                         int yearOfProduction, int horsepower,
                         int acceleration, int suspension, int durability) {
        Car car = null;
        switch (type) {
            case PERFORMANCE_CAR_TYPE:
                car = CarFactory.createPerformanceCar(brand, model, yearOfProduction,
                        horsepower, acceleration, suspension, durability);
                break;
            case SHOW_CAR_TYPE:
                car = CarFactory.createShowCar(brand, model, yearOfProduction,
                        horsepower, acceleration, suspension, durability);
                break;

        }
        if (!this.cars.containsKey(id)) {
            cars.put(id, car);
        }
    }

    public String check(int id) {
        if (this.cars.containsKey(id)) {
            return this.cars.get(id).toString();
        }
        return null;

    }

    public void open(int id, String type, int length, String route, int prizePool, int... op) {
        if (this.races.containsKey(id)) {
            return;
        }
        Race race = null;
        switch (type) {
            case DRIFT_RACE_TYPE:
                race = RaceFactory.createDriftRace(length, route, prizePool);
                break;
            case DRAG_RACE_TYPE:
                race = RaceFactory.createDragRace(length, route, prizePool);
                break;
            case CASUAL_RACE_TYPE:
                race = RaceFactory.createCasualRace(length, route, prizePool);
                break;
            case TIME_LIMIT_RACE_TYPE:
                race = RaceFactory.createTimeLimitRace(length, route, prizePool, op[0]);
                break;
            case CIRCUIT_RACE_TYPE:
                race = RaceFactory.createCirculateRace(length, route, prizePool, op[0]);
                break;
        }
        races.put(id, race);
    }

    public void participate(int carId, int raceId) {
        if (this.cars.containsKey(carId) &&
                this.races.containsKey(raceId) &&
                !this.garage.getCars().contains(this.cars.get(carId))) {
            if(this.races.get(raceId).getClass().getSimpleName().contains(TIME_LIMIT_RACE_TYPE) &&
                    this.races.get(raceId).getParticipants().size() > 0){
                return;
            }
            this.races.get(raceId).addParticipant(cars.get(carId));
        }
    }

    public String start(int id) {
        if (this.races.containsKey(id)) {
            Race race = races.get(id);
            if (race.getParticipants().size() > 0) {
                return race.toString();
            } else {
                return CANNOT_START_THE_RACE_WITH_ZERO_PARTICIPANTS;
            }
        }
        return null;
    }

    public void park(int id) {
        if (this.cars.containsKey(id)) {
            Car car = cars.get(id);
            long countOfRaces = this.races.values().stream()
                    .filter(r -> r.getParticipants().contains(car)).count();
            if (countOfRaces == 0) {
                this.garage.park(car);
            }
        }
    }

    public void unpark(int id) {
        if (this.garage.getCars().size() > 0) {
            Car car = cars.get(id);
            if (this.garage.getCars().contains(car)) {
                this.garage.unPark(car);
            }
        }
    }

    public void tune(int tuneIndex, String addOn) {
        if (garage.getCars().size() > 0) {
            for (Car car : this.garage.getCars()) {
                car.tune(tuneIndex, addOn);
            }
        }
    }
}
