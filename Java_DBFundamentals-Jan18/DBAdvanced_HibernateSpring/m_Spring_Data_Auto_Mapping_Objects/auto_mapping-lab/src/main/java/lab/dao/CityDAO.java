package lab.dao;


import lab.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityDAO extends JpaRepository<City, Long> {

    Optional<City> findById(Long index);

    List<City> findAll();

}
