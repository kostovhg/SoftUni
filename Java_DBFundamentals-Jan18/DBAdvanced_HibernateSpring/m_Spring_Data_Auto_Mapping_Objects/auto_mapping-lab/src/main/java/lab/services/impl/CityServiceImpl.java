package lab.services.impl;

import lab.dao.CityDAO;
import lab.models.entities.City;
import lab.services.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityDAO cityDAO;

    @Autowired
    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> findAll() {
        return this.cityDAO.findAll();
    }

    @Override
    public void saveAll(List<City> cities) {
        this.cityDAO.saveAll(cities);
    }

    @Override
    public void save(City city){
        this.cityDAO.saveAndFlush(city);
    }

    @Override
    public City findById(Long index) {
        return this.cityDAO.getOne(index);
    }
}
