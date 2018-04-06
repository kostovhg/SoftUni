package lab.services.impl;

import lab.dao.AddressDAO;
import lab.services.api.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAODAO;

    @Autowired
    public AddressServiceImpl(AddressDAO employeeDAO) {
        this.addressDAODAO = employeeDAO;
    }

    @Override
    public void saveAll(List<lab.models.entities.Address> addressesList) {
        this.addressDAODAO.saveAll(addressesList);
    }

    @Override
    public void save(lab.models.entities.Address address){
        this.addressDAODAO.saveAndFlush(address);
    }

    @Override
    public List<lab.models.entities.Address> findAll() {
        return this.addressDAODAO.findAll();
    }

    @Override
    public Optional<lab.models.entities.Address> findById(Long id) {
        return this.addressDAODAO.findById(id);
    }
}
