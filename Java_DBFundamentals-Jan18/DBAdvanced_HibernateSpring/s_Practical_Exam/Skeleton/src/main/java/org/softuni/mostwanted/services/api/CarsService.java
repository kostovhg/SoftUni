package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.binding.cars.CarJSONImportDto;
import org.softuni.mostwanted.entity.Car;

public interface CarsService {

    boolean create(CarJSONImportDto a);

    Car findById(Integer integer);
}
