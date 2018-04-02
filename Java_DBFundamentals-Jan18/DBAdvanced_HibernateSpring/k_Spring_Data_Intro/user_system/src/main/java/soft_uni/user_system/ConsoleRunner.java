package soft_uni.user_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.townEntity.Country;
import soft_uni.user_system.models.entities.townEntity.Town;
import soft_uni.user_system.models.services.servicesImpl.*;

import javax.validation.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
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
            this.SeedDatabase();
        }

        System.out.println("+-----------------------------------------------------+");
        System.out.println("+ Problem 1. Get User by Email Provider               +");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        System.out.println("Enter domain of email provider: ");
        String domain = reader.readLine();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("+ Problem 1. Result                                   +");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        List<User> users = this.userService.getAllWhereEmailLike(domain);
        if (users.size() > 1) {
            for (User u : users) {
                System.out.println(String.format("%s %s", u.getUsername(), u.getEmail()));
            }
        } else {
            System.out.println("There is no users with email provider " + domain);
        }
        System.out.println();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("+ Problem 2. Remove Inactive Users                    +");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        System.out.println("Enter date in format <dd MMM yyyy> to mark as inactive all users with last login before it: ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = dateFormat.parse(reader.readLine());

        System.out.println("+-----------------------------------------------------+");
        System.out.println("+ Problem 2. Result                                   +");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();

        List<User> inactiveUsers = this.userService.getAllInactiveUsers(date);
        if (users.size() > 0) {
            inactiveUsers.forEach(u -> u.setDeleted(true));
            //userService.deleteAllInactive();
            System.out.printf("%d user%s been deleted",
                    users.size(), users.size() > 1 ? "s has" : " have");
        } else {
            System.out.println("No users have been deleted " + domain);
        }
        System.out.println();

        System.out.println("+-----------------------------------------------------+");
        System.out.println("+ Ending program                                      +");
        System.out.println("+-----------------------------------------------------+");
        System.out.println();
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

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
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

            Set<ConstraintViolation<User>> violations = validator.validate(user);
            if(violations.size() < 1) {
                users.add(user);
            } else {
                for (ConstraintViolation<User> violation : violations) {
                    System.out.printf("Error: %s%n", violation.getMessage());
                }
            }
        }

        this.userService.saveUserToDatabase(users);

    }

}
