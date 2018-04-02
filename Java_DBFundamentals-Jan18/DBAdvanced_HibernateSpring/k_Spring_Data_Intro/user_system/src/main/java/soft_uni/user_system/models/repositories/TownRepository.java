package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.townEntity.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Town findByName(String townName);
}
