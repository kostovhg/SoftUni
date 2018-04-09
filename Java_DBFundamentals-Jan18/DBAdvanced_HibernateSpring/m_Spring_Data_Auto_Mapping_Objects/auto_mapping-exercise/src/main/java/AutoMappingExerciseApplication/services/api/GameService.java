package AutoMappingExerciseApplication.services.api;

import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.models.entities.Game;

import java.util.stream.Stream;

public interface GameService {

    String addGame(GameDto gameDto);

    String editGame(GameDto gameDto, Long id);

    String deleteGame(Long id);

    Stream<String> getAllGames();

    String gameDetails(String title);

    Game gameByTitle(String title);
}
