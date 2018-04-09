package AutoMappingExerciseApplication.repositories;

import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Game getFirstByTitle(String title);

}
