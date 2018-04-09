package AutoMappingExerciseApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
public class AutoMappingExerciseApplication {
    //System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "local");
    public static void main(String[] args) {
        SpringApplication.run(AutoMappingExerciseApplication.class, args);
    }
}
