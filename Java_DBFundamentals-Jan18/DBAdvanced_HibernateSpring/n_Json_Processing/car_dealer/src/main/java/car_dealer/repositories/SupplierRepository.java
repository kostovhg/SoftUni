package car_dealer.repositories;

import car_dealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

//    @Query("SELECT s FROM Supplier AS s WHERE s.isImporter = 0")
//    List<Supplier> getAllLocalSuppliers();

}
