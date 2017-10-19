import java.util.Arrays;
import java.util.Scanner;

public class p02_CubicsRube {
    private static int size;
    private static int volume;
    private static int[] coordinates;
    private static Long particles = 0L;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        size = Integer.parseInt(scanner.nextLine());
        volume = size * size * size;
        String input;
        while(!"Analyze".equals(input = scanner.nextLine())){
            coordinates = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            boolean valid = true;
            for (int i = 0; i < 3 ; i++) {
                if(coordinates[i] < 0 || coordinates[i] >= size){
                    valid = false;
                }
            }
            if(!valid) continue;
            if(coordinates[3] > 0) volume--;
            particles += coordinates[3];
        }

        System.out.println(particles);
        System.out.println(volume);

    }
}
