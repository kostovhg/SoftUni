package car_dealer.services.impl;

import car_dealer.dto.bingings.CarDto;
import car_dealer.dto.models.CarByMakeModel;
import car_dealer.dto.models.CarModel;
import car_dealer.dto.models.CarPartsModel;
import car_dealer.dto.models.PartModel;
import car_dealer.entities.Car;
import car_dealer.entities.Part;
import car_dealer.repositories.CarRepository;
import car_dealer.repositories.PartRepository;
import car_dealer.services.api.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private PartRepository partRepository;
    private ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Car car){
        this.carRepository.save(car);
    }

    @Override
    public void saveAll(CarDto[] cars){
        Random random = new Random();
        List<Part> allParts = this.partRepository.findAll();
        for (CarDto carDto : cars) {
            Car car = this.mapper.map(carDto, Car.class);
            int numberOfParts = random.nextInt(11) + 10;
            for (int i = 0; i < numberOfParts; i++) {
                 int randomIndex = random.nextInt(allParts.size());
                 car.addPart(allParts.get(randomIndex));
            }
            this.save(car);
        }
    }

    @Override
    public List<CarByMakeModel> getCarsByMake(String make){
        List<Car> cars = this.carRepository.findByMakeOrderByModelAscTravelledDistanceDesc(make);
        List<CarByMakeModel> carModels = new ArrayList<>();
        for (Car car : cars) {
            CarByMakeModel carModel = this.mapper.map(car, CarByMakeModel.class);
            carModels.add(carModel);
        }
        return carModels;
    }

    @Override
    public List<CarPartsModel> getAllCarsWithTHeirParts(){
        List<Car> cars = this.carRepository.findAll();
        List<CarPartsModel> carPartsModels = new ArrayList<>();
        for (Car car : cars) {
            CarPartsModel carPartsModel = new CarPartsModel();
            CarModel carModel = this.mapper.map(car, CarModel.class);
            carPartsModel.setCar(carModel);
            for (Part part : car.getParts()) {
                PartModel partModel = this.mapper.map(part, PartModel.class);
                carPartsModel.getParts().add(partModel);
            }

            carPartsModels.add(carPartsModel);
        }
        return carPartsModels;
    }
}
