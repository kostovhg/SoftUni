package soft_uni.user_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.services.servicesImpl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserServiceImpl userService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter domain of email provider: ");
        String domain = reader.readLine();

        List<User> users = this.userService.getAllWhereEmailLike(String.format("%%s",domain));
        for (User user : users) {
            System.out.println(String.format("%s %s", user.getUsername(), user.getEmail()));
        }
    }
}
