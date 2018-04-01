package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.albumEntity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
