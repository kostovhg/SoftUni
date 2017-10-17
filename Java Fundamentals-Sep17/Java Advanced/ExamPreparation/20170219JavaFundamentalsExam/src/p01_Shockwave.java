import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p01_Shockwave {
    private static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        List<Integer> tokens = Arrays.stream(input.split("\\s+"))
                .map(Integer::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        int rows = tokens.get(0);
        int cols = tokens.get(1);
        room = new int[rows][cols];

        while(!(input = reader.readLine()).equalsIgnoreCase("Here We Go")){
            tokens.clear();
            tokens.addAll(Arrays.stream(input
                    .split("\\s+")).map(Integer::valueOf).collect(Collectors.toList()));
            for (int row = tokens.get(0); row <= tokens.get(2) ; row++) {
                for (int col = tokens.get(1); col <= tokens.get(3); col++) {
                    room[row][col]++;
                }
            }
        }

        Arrays.stream(room).forEach(r -> {
                Arrays.stream(r).forEach(c -> System.out.printf("%s ", c));
                System.out.println();
            });

    }
}
