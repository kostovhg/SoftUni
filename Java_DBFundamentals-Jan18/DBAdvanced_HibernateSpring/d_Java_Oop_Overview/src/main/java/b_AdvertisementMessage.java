import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class b_AdvertisementMessage {
    
    private static String[] PHRASES = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
    private static String[] EVENTS = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
    private static String[] AUTHORS = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
    private static String[] CITIES = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int messagesCount = Integer.parseInt(reader.readLine());

        Random rnd = new Random();

        for (int i = 0; i < messagesCount; i++) {
            System.out.println(
                    String.format("%s %s %s - %s",
                            PHRASES[rnd.nextInt(PHRASES.length)],
                            EVENTS[rnd.nextInt(EVENTS.length)],
                            AUTHORS[rnd.nextInt(AUTHORS.length)],
                            CITIES[rnd.nextInt(CITIES.length)])
            );
        }

    }

}