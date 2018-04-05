package system_bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class SystemBookshopApplication {

    public static void main(String[] args) {
        // Using application-local.properties with spring.datasource.password set to local mysql password
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "local");
        SpringApplication.run(SystemBookshopApplication.class, args);
    }
}
