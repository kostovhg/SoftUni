package AutoMappingExerciseApplication.services.impl;

import AutoMappingExerciseApplication.repositories.GameRepository;
import AutoMappingExerciseApplication.dto.GameDto;
import AutoMappingExerciseApplication.models.entities.Game;
import AutoMappingExerciseApplication.services.api.GameService;
import AutoMappingExerciseApplication.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public String addGame(GameDto gameDto){
        Game game = Mapper.getInstance().map(gameDto, Game.class);

        try{
            this.gameRepository.save(game);
        } catch (ConstraintViolationException cve){
            return cve.getMessage();
        }

        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(GameDto gameDto, Long id) {

        Game game = this.gameRepository.findOne(id);
        if(game == null){
            return "There is no game with id " + id;
        }

        try{
            Class<?> gameDtoClass = gameDto.getClass();
            Field[] dtoFields = gameDtoClass.getDeclaredFields();
            Class<?> gameClass = game.getClass();
            for (Field dtoField : dtoFields) {
                dtoField.setAccessible(true);
                if(dtoField.get(gameDto) != null){
                    Object value = dtoField.get(gameDto);
                    String setterName = "set" + dtoField.getName();
                    List<Method> appropriatedMethods = Arrays.asList(gameClass.getDeclaredMethods()).stream()
                            .filter(m -> m.getName().equalsIgnoreCase(setterName)).collect(Collectors.toList());

                    for (Method appropriatedMethod : appropriatedMethods) {
                        if(appropriatedMethod.getParameterTypes()[0] == value.getClass()){
                            appropriatedMethod.invoke(game, value);
                        }
                    }
                }
            }
        } catch ( IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        this.gameRepository.save(game);
        return String.format("Edited %s", game.getTitle());
    }

    @Override
    public String deleteGame(Long id){
        Game game = this.gameRepository.findOne(id);
        if(game == null){
            return "There is no game with id " + id;
        }

        this.gameRepository.delete(game);
        return String.format("Deleted %s", game.getTitle());
    }

    @Override
    public Stream<String> getAllGames() {
        return this.gameRepository.findAll().stream().map(g -> String.format("%s %.2f", g.getTitle(), g.getPrice()));
    }

    @Override
    public String gameDetails(String title) {
        GameDto game = Mapper.getInstance().map(this.gameRepository.getFirstByTitle(title), GameDto.class);

        if(game == null){
            return "There is no game with title: " + title;
        }
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("Title: %s\nPrice: %.2f\nDescription: %s\nReleaseDate: %s\n",
                game.getTitle(), game.getPrice(), game.getDescription(), df.format(game.getReleaseDate()));
    }

    @Override
    public Game gameByTitle(String title) {
        return this.gameRepository.getFirstByTitle(title);
    }


}
