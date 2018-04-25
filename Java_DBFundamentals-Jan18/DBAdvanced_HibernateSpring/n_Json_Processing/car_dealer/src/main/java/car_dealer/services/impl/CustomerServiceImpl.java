package car_dealer.services.impl;

import car_dealer.dto.bingings.CustomerDto;
import car_dealer.dto.models.CustomerByBirthdateModel;
import car_dealer.dto.models.CustomerPurchasesModel;
import car_dealer.entities.Customer;
import car_dealer.repositories.CustomerRepository;
import car_dealer.services.api.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void saveAll(CustomerDto[] customers) {
        for (CustomerDto customerDto : customers) {
            Customer customer = this.mapper.map(customerDto, Customer.class);
            this.save(customer);
        }
    }

    @Override
    public List<CustomerByBirthdateModel> getAllSortedByBirthDate() {
        List<Customer> customers = this.customerRepository.getAllByBirthDate();
        List<CustomerByBirthdateModel> customersModels = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerByBirthdateModel customerModel = this.mapper.map(customer, CustomerByBirthdateModel.class);
            customersModels.add(customerModel);
        }
        return customersModels;
    }

    @Override
    public List<CustomerPurchasesModel> getAllCustomersWithPurchases() {
        return this.customerRepository.getAllCustomerWithPurchases();
    }
}
