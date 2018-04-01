package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.albumEntity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
