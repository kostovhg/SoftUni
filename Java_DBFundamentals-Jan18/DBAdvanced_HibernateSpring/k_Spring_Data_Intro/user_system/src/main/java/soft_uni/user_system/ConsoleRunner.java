package soft_uni.user_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.townEntity.Country;
import soft_uni.user_system.models.entities.townEntity.Town;
import soft_uni.user_system.models.services.servicesImpl.*;

import javax.validation.Valid;
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

        if (userService.getAll().size() < 1) {
            try {
                this.SeedDatabase();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Enter domain of email provider: ");
        String domain = reader.readLine();

        List<User> users = this.userService.getAllWhereEmailLike(domain);
        for (User u : users) {
            System.out.println(String.format("%s %s", u.getUsername(), u.getEmail()));
        }
    }

    private void SeedDatabase() throws ParseException {

        List<Country> countries = new ArrayList<>();
        List<Town> towns = new ArrayList<>();
        // First seeding the towns and countries
        for (String countryName : TOWNS.keySet()) {
            Country country = new Country(countryName);
            countries.add(country);
            //this.countryService.saveCountryToDatabase(country);
            for (String town : TOWNS.get(countryName)) {
                Town newTown = new Town(town, country);
                towns.add(newTown);
                //this.townService.saveTownToDatabase(newTown);
            }
        }

        this.countryService.saveCountryToDatabase(countries);
        this.townService.saveTownToDatabase(towns);

        // seed users.
        Random rnd = new Random();
        List<@Valid User> users = new ArrayList<>();
        for (String[] userData : USERS) {
            User user = new User(userData[0],
                    userData[1],
                    userData[2],
                    DATE_FORMAT.parse(userData[3]));
            user.setBornTown(towns.get(rnd.nextInt(10)));
            user.setCurrentlyLiving(towns.get(rnd.nextInt(10 - 1)));
            user.setAge(10 + rnd.nextInt(60));

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -120); // TODO: it should be in the interval from now back to reg_date
            user.setLastTimeLoggedIn(c.getTime());

            //users.add(user);
            try {
                userService.saveUserToDatabase(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // not the case because whe should check every user separately
        //this.userService.saveUserToDatabase(users);

    }

}
