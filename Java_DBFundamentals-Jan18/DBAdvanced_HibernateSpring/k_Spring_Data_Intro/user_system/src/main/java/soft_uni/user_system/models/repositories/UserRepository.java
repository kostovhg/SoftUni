package soft_uni.user_system.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.user_system.models.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query("SELECT u FROM User AS u WHERE u.email LIKE :lookup")
    List<User> findAllByEmailEndsWith(String lookupString);

    User getByUsername(String username);

    List<User> findAllByLastTimeLoggedInLessThan(Date date);

    void deleteAllByDeleted(boolean isDeleted);

}
