package car_dealer.services.api;

import car_dealer.dto.bingings.CarDto;
import car_dealer.dto.models.CarByMakeModel;
import car_dealer.dto.models.CarPartsModel;
import car_dealer.entities.Car;

import java.util.List;

public interface CarService {
    void save(Car car);

    void saveAll(CarDto[] cars);

    List<CarByMakeModel> getCarsByMake(String make);

    List<CarPartsModel> getAllCarsWithTHeirParts();
}
