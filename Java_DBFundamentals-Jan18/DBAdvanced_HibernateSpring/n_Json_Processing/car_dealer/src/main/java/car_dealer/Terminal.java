package car_dealer;

import car_dealer.dto.bingings.CarDto;
import car_dealer.dto.bingings.CustomerDto;
import car_dealer.dto.bingings.PartDto;
import car_dealer.dto.bingings.SupplierDto;
import car_dealer.io.JsonParser;
import car_dealer.services.api.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static car_dealer.utils.Constants.*;

@Component
public class Terminal implements CommandLineRunner {

    private SaleService saleService;
    private CustomerService customerService;
    private PartService partService;
    private SupplierService supplierService;
    private CarService carService;
    private JsonParser jsonParser;

    @Autowired
    public Terminal(SaleService saleService, CustomerService customerService, PartService partService, SupplierService supplierService, CarService carService, JsonParser jsonParser) {
        this.saleService = saleService;
        this.customerService = customerService;
        this.partService = partService;
        this.supplierService = supplierService;
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... args) throws Exception {

        carDealerImportData();
        System.out.println("test");
    }

    private void carDealerImportData() throws IOException {
        // Import suppliers
        Type type = new TypeToken<ArrayList<SupplierDto>>() {
        }.getType();
        List<SupplierDto> supplierDtos = this.jsonParser.importJsonList(type, JSON_INPUT_FILES_DIRECTORY + SUPPLIERS_INPUT_JSON);

        this.supplierService.saveAll(supplierDtos);

        // Import parts
        type = new TypeToken<ArrayList<PartDto>>() {
        }.getType();
        PartDto[] partsDtos = this.jsonParser.importJson(PartDto[].class, JSON_INPUT_FILES_DIRECTORY + PARTS_INPUT_JSON);

        this.partService.saveAll(partsDtos);

        // Import cars
        CarDto[] carsDtos = this.jsonParser.importJson(CarDto[].class, JSON_INPUT_FILES_DIRECTORY + CARS_INPUT_JSON);
        this.carService.saveAll(carsDtos);

        // Import Customers
        CustomerDto[] customerDtos = this.jsonParser.importJson(CustomerDto[].class, JSON_INPUT_FILES_DIRECTORY + CUSTOMER_INPUT_JSON);
        this.customerService.saveAll(customerDtos);

        // Import Sales
        this.saleService.insertRandomData();


        System.out.println("test");
    }

}
