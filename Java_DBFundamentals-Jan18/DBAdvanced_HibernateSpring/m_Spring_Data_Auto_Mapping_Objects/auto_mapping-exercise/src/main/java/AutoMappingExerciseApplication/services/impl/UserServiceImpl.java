package AutoMappingExerciseApplication.services.impl;

import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.dto.LoginDto;
import AutoMappingExerciseApplication.dto.RegisterUserDto;
import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.models.entities.User;
import AutoMappingExerciseApplication.repositories.GameRepository;
import AutoMappingExerciseApplication.repositories.UserRepository;
import AutoMappingExerciseApplication.services.api.UserService;
import AutoMappingExerciseApplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public String registerUser(RegisterUserDto userDto){
        if(!userDto.getConfirmPassword().equals(userDto.getPassword())){
            return "The password confirmation doesn't match";
        }
        User user = Mapper.getInstance().map(userDto, User.class);
        if(this.userRepository.count() == 0){
            user.setAdmin(true);
        } else if (userRepository.countDistinctByEmail(user.getEmail()) > 0){
            return "You can not register user with existing email";
        }

        try{
            this.userRepository.save(user);
        } catch (ConstraintViolationException cve){
            StringBuilder sb = new StringBuilder();
            cve.getConstraintViolations().iterator()
                    .forEachRemaining(s -> sb.append(s.getMessageTemplate()).append(System.lineSeparator()));
            return sb.toString();
        }
        return String.format("%s was registered", user.getFullName());
    }

    @Override
    public User getUserWithGames(Long id){
        return this.userRepository.getOneWithGames(id);
    }

    @Override
    public User login(LoginDto dto){
        return this.userRepository.getLoggedInUser(dto.getEmail(), dto.getPassword());
    }

    @Override
    public String setBoughtItems(Set<GameDto> gameDtos, Long id){
        User user = this.userRepository.findOne(id);
        String gamesTitles = String.join(System.lineSeparator() + " -",
                gameDtos.stream()
        .map(GameDto::getTitle)
        .collect(Collectors.toSet()));

        Set<Game> games = gameDtos.stream()
                .map(gameDto -> this.gameRepository.getFirstByTitle(gameDto.getTitle()))
                .collect(Collectors.toSet());
        user.setGames(games);
        this.userRepository.save(user);
        return String.format("Successfully bought games: \n -$s", gamesTitles);
    }
}
