package car_dealer.services.impl;

import car_dealer.dto.models.CarModel;
import car_dealer.dto.models.SaleModel;
import car_dealer.entities.Car;
import car_dealer.entities.Customer;
import car_dealer.entities.Sale;
import car_dealer.repositories.CarRepository;
import car_dealer.repositories.CustomerRepository;
import car_dealer.repositories.SaleRepository;
import car_dealer.services.api.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Sale sale){
        this.saleRepository.save(sale);
    }

    @Override
    public void insertRandomData(){
        double[] discounts = {0.0, 0.05, 0.1, 0.15, 0.2, 0.4, 0.5};
        List<Car> allCars = this.carRepository.findAll();
        List<Customer> allCustomers = this.customerRepository.findAll();
        Random random = new Random();
        for (int i = 0; i < 150; i++) {
            Sale sale = new Sale();
            int randomIndex = random.nextInt(discounts.length);
            sale.setDiscount(discounts[randomIndex]);
            randomIndex = random.nextInt(allCars.size());
            sale.setCar(allCars.get(randomIndex));
            randomIndex = random.nextInt(allCustomers.size());
            sale.setCustomer(allCustomers.get(randomIndex));
            this.save(sale);
        }
    }

    @Override
    public List<SaleModel> getAllSaleDetails() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleModel> saleModels = new ArrayList<>();
        for (Sale sale : sales) {
            SaleModel saleModel = new SaleModel();
            CarModel carModel = this.mapper.map(sale.getCar(), CarModel.class);
            saleModel.setCar(carModel);
            saleModel.setCustomerName(sale.getCustomer().getName());
            saleModel.setDiscount(sale.getDiscount());
            saleModel.setPrice(sale.getCar().getPrice());
            BigDecimal priceWIthDiscount = saleModel.getPrice()
                    .multiply(BigDecimal.valueOf(1.00 - saleModel.getDiscount()));
            saleModel.setPriceWithDiscount(priceWIthDiscount);
            saleModels.add(saleModel);
        }

        return saleModels;
    }
}
