package lab.dao;

import lab.models.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDAO extends JpaRepository<lab.models.entities.Address, Long> {

}
