package need_for_speed.entities.garage;

import need_for_speed.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage {
    private final List<Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    public void park(Car car){
        this.parkedCars.add(car);
    }

    public void unPark(Car car){
        this.parkedCars.remove(car);
    }

    public List<Car> getCars(){
        return Collections.unmodifiableList(this.parkedCars);
    }
}
