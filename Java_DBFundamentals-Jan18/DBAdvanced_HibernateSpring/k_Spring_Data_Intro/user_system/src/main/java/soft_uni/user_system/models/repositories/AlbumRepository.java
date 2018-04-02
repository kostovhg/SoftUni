package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.albumEntity.Album;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    //@Query("SELECT a FROM Album AS a WHERE a.owner = :")
    List<Album> getAllByOwner(User owner);
}
