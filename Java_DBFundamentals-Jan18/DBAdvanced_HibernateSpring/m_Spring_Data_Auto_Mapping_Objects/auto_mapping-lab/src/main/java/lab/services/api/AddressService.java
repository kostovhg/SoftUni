package lab.services.api;

import lab.models.entities.Address;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface AddressService {


    void saveAll(List<Address> addressesList);

    void save(Address address);

    List<Address> findAll();

    Optional<Address> findById(Long id);
}
