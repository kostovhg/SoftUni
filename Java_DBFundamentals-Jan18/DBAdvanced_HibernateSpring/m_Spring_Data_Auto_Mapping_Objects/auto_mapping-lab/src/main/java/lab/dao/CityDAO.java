package lab.dao;


import lab.models.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityDAO extends JpaRepository<City, Long> {

    Optional<City> findById(Long index);

    List<City> findAll();

}
