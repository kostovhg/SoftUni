package lab.services.api;

import lab.models.entities.City;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface CityService {

    void saveAll(List<City> cities);

    void save(City city);

    City findById(Long index);

    List<City> findAll();
}
