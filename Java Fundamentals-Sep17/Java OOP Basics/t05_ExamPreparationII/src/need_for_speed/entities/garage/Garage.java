package need_for_speed.entities.garage;

import need_for_speed.entities.cars.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Garage {
    private final List<Car> parkedCars;

    public Garage(List<Car> cars) {
        this.parkedCars = cars;
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

    public void tuneCars(int index, String addOn){
        this.parkedCars.forEach(c -> c.tune(index, addOn));
    }
}
