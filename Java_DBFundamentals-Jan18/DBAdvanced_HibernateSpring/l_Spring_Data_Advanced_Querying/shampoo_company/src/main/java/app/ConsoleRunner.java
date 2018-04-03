package app;

import app.model.enums.Size;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import app.services.LabelServiceImpl;
import app.services.ShampooServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * To use this instead of demo() from Application, insert @Component annotation
 */
public class ConsoleRunner implements CommandLineRunner {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    private ShampooServiceImpl shampooService;
    private LabelServiceImpl labelService;

    @Autowired
    public ConsoleRunner(ShampooServiceImpl shampooService, LabelServiceImpl labelService) {
        ;
        this.shampooService = shampooService;
        this.labelService = labelService;
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("Enter the size (SMALL, MEDIUM, LARGE): ");
        Size size = Size.valueOf(READER.readLine().toUpperCase());

        // Task 1
        this.shampooService.getAllBySize(size).forEach(s ->
                System.out.printf("%s %s %.2flv.%n",
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));

        // Task 2
        System.out.println("Enter the size (SMALL, MEDIUM, LARGE): ");
        size = Size.valueOf(READER.readLine().toUpperCase());
        System.out.println("Enter label id: ");
        BasicLabel label = this.labelService.findById(Long.valueOf(READER.readLine()));
        List<BasicShampoo> shampoos =
                this.shampooService.getAllBySizeOrLabel_IdOrderByPriceAsc(size, 10l);
        shampoos.stream()
                .forEach(s -> System.out.printf("%s %s %.2flv.%n",
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()));
        // All tings all same as in Application demo() method

    }
}
