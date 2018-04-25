package xmlProcessingE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xmlProcessingE.domain.model.Category;
import xmlProcessingE.domain.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User AS u " +
            "JOIN Product AS p ON p.seller = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id")
    List<User> findAllUsersWithSoldProducts();

    User findUserById(long id);


}
