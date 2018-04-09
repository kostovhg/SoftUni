package AutoMappingExerciseApplication.services.api;

import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.dto.LoginDto;
import AutoMappingExerciseApplication.dto.RegisterUserDto;
import AutoMappingExerciseApplication.models.entities.User;

import java.util.Set;

public interface UserService {

    String registerUser(RegisterUserDto userDto);

    User getUserWithGames(Long id);

    User login(LoginDto dto);

    String setBoughtItems(Set<GameDto> gameDtos, Long id);
}
