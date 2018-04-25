package car_dealer.services.api;

import car_dealer.dto.bingings.CustomerDto;
import car_dealer.dto.models.CustomerByBirthdateModel;
import car_dealer.dto.models.CustomerPurchasesModel;
import car_dealer.entities.Customer;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    void saveAll(CustomerDto[] customers);

    List<CustomerByBirthdateModel> getAllSortedByBirthDate();

    List<CustomerPurchasesModel> getAllCustomersWithPurchases();
}
