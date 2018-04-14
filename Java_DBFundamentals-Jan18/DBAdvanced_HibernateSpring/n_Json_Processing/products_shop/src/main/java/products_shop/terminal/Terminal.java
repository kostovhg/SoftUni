package products_shop.terminal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {


        System.out.println("Test");
    }
}
