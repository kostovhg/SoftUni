package car_dealer.repositories;

import car_dealer.dto.models.CustomerPurchasesModel;
import car_dealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer AS c ORDER BY c.birthDate, c.youngDriver")
    List<Customer> getAllByBirthDate();

    @Query("SELECT new car_dealer.dto.models.CustomerPurchasesModel(c.name, c.purchases.size, SUM(p.car.price)) " +
            "FROM Customer AS c JOIN c.purchases AS p " +
            "GROUP BY c.name " +
            "ORDER BY SUM(p.car.price) DESC, c.purchases.size DESC")
    List<CustomerPurchasesModel> getAllCustomerWithPurchases();
}
