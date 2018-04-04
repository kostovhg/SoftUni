package system_bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource(value = {
//        "classpath:application.properties",
//        "classpath:application-local.properties",
//        "classpath:params.properties"
//})
public class SystemBookshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemBookshopApplication.class, args);
    }
}
