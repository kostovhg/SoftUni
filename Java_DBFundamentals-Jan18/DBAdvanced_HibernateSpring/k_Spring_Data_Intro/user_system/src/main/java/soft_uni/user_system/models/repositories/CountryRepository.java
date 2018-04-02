package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.townEntity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);
}
