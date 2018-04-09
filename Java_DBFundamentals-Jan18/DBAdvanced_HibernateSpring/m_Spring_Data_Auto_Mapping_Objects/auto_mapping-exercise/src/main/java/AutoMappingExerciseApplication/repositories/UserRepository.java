package AutoMappingExerciseApplication.repositories;

import AutoMappingExerciseApplication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User AS u " +
            "LEFT JOIN fetch u.orders " +
            "LEFT JOIN fetch u.games " +
            "WHERE u.email = :email " +
            "AND u.password = :password")
    User getLoggedInUser(
            @Param("email") String email,
            @Param("password") String password
    );


    @Query("SELECT u FROM User AS u " +
            "LEFT JOIN FETCH u.games " +
            "WHERE u.id =:id")
    User getOneWithGames(@Param("id") Long id);

    int countDistinctByEmail(String email);
}
