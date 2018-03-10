package exercises.i_StudentsEnrolledIn2014or2015;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;

        while (!"end".equalsIgnoreCase(input = reader.readLine())) {
            String[] tokens = input.split("\\s+");
            if (tokens[0].endsWith("15") || tokens[0].endsWith("14")){
                Arrays.stream(tokens)
                        .skip(1)
                        .forEach(x -> System.out.print(x + " "));
                System.out.println();
            }
        }
    }
}