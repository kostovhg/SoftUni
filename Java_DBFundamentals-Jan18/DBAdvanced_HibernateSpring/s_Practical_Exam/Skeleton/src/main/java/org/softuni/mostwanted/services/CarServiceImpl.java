package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.cars.CarJSONImportDto;
import org.softuni.mostwanted.entity.Car;
import org.softuni.mostwanted.entity.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.services.api.CarsService;
import org.softuni.mostwanted.services.api.RacersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarsService {

    private final CarRepository carRepository;
    private final RacersService racersService;
    private final ModelParser modelParser;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacersService racersService, ModelParser modelParser) {
        this.carRepository = carRepository;
        this.racersService = racersService;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(CarJSONImportDto carDto) {
        try {
            Car car = this.modelParser.convert(carDto, Car.class);
            if (!this.carRepository.findAll().contains(car)){
                Racer racer = this.racersService.findOneByName(carDto.getRacerName());
                car.setRacer(racer);
                racer.getCars().add(car);
                this.carRepository.saveAndFlush(car);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Car findById(Integer integer) {
        return this.carRepository.findById(integer);
    }

}
