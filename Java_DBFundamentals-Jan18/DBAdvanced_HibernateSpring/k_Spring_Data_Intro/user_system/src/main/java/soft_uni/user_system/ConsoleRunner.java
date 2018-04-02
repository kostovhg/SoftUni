package soft_uni.user_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.townEntity.Country;
import soft_uni.user_system.models.entities.townEntity.Town;
import soft_uni.user_system.models.services.servicesImpl.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static soft_uni.user_system.utils.Constants.TOWNS;
import static soft_uni.user_system.utils.Constants.USERS;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
    private static final Date TODAY = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    private UserServiceImpl userService;
    private TownServiceImpl townService;
    private CountryServiceImpl countryService;
    private AlbumServiceImpl albumService;
    private PictureServiceImpl pictureService;

    @Autowired
    public ConsoleRunner(
            UserServiceImpl userService,
            TownServiceImpl townService,
            CountryServiceImpl countryService,
            AlbumServiceImpl albumService,
            PictureServiceImpl pictureService) {
        this.userService = userService;
        this.townService = townService;
        this.countryService = countryService;
        this.albumService = albumService;
        this.pictureService = pictureService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try{
            this.SeedDatabase();
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Enter domain of email provider: ");
        String domain = reader.readLine();
        /*
        Country country = new Country();
        country.setName("Bulgaria");

        Town town = new Town();
        town.setCountry(country);
        town.setName("Sofia");

        countryService.saveCountryToDatabase(country);
        townService.saveTownToDatabase(town);

        Picture picture = new Picture();
        picture.setPath(AVATARS_FOLDER + "hidden_cat.jpg");
        picture.setImage(new byte[10]);
        picture.setTitle("hidden cat");
        picture.setCaption("just a random image");
        pictureService.savePictureToDatabase(picture);

        Album peshosAlbum = new Album();
        Album ivansAlbum = new Album();
        peshosAlbum.getPictures().add(picture);
        ivansAlbum.getPictures().add(picture);
        peshosAlbum.setAlbumName("First Album");
        ivansAlbum.setAlbumName("First Album");
        peshosAlbum.setBackgroundColor(Color.getColor("Blue").idOf());

        User pesho = new User();
        User ivan = new User();
        pesho.setUsername("pekata99");
        ivan.setUsername("vancho");
        pesho.setAge(20);
        ivan.setAge(20);
        pesho.setProfilePicture(picture.getPath());
        ivan.setProfilePicture(picture.getPath());
        pesho.setCurrentlyLiving(town);
        ivan.setCurrentlyLiving(town);
        pesho.setBornTown(town);
        ivan.setBornTown(town);

        pesho.setEmail("petar_petrov@gmail.com");
        ivan.setEmail("vancho.ivanov@abv.bg");
        pesho.setPassword("Ver1Com!ca7eD");
        ivan.setPassword("HeME(3AHuMaBau)");
        pesho.setFirstName("Petar");
        ivan.setFirstName("Ivan");
        pesho.setLastName("Petrov");
        ivan.setLastName("Ivanov");
        pesho.getFriends().add(ivan);


        userService.saveUserToDatabase(pesho);
        userService.saveUserToDatabase(ivan);

        peshosAlbum.setOwner(pesho);
        ivansAlbum.setOwner(ivan);

        albumService.saveAlbumToDatabase(peshosAlbum);
        albumService.saveAlbumToDatabase(ivansAlbum);
        */

        List<User> users = this.userService.getAllWhereEmailLike(domain);
        for (User u : users) {
            System.out.println(String.format("%s %s", u.getUsername(), u.getEmail()));
        }
    }

    private void SeedDatabase() throws ParseException {

        // First seeding the towns and countries
        for (String countryName : TOWNS.keySet()) {
            Country country = new Country(countryName);
            this.countryService.saveCountryToDatabase(country);
            for (String town : TOWNS.get(countryName)) {
                Town newTown = new Town(town, country);
                this.townService.saveTownToDatabase(newTown);
            }
        }

        // seed users.
        Random rnd = new Random();
        for (String[] userData : USERS) {
            User user = new User(userData[0],
                    userData[1],
                    userData[2],
                    DATE_FORMAT.parse(userData[3]));
            user.setBornTown(townService.getByTownName(TOWNS.get("Bulgaria")[rnd.nextInt(10)]));
            user.setCurrentlyLiving(townService.getByTownName(TOWNS.get("Bulgaria")[rnd.nextInt(10)]));
            user.setAge(10 + rnd.nextInt(80));

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -120); // TODO: it should be in the interval from now back to reg_date
            user.setLastTimeLoggedIn(c.getTime());

            this.userService.saveUserToDatabase(user);
        }

    }

}
