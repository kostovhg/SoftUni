import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class p13_InvernoIII {
    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    private static Integer[] gems;
    private static HashMap<String, Predicate<Integer>> commands = new HashMap<>();

    public static void main(String[] args) throws IOException {
        gems = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::valueOf).toArray(Integer[]::new);
        while(true){
            String[] input = reader.readLine().split(";");
            if("Forge".equals(input[0])) {
                break;
            }
            Predicate<Integer> predicate = makePredicate(input);
            if("Exclude".equalsIgnoreCase(input[0])){
                commands.put(input[1]+input[2], predicate);
            } else {
                commands.remove(input[1]+input[2]);
            }
        }
        for (int i = 0; i < gems.length; i++) {
            boolean forRemove = false;
            for (Map.Entry<String, Predicate<Integer>> stringPredicateEntry :
            commands.entrySet()){
                if(stringPredicateEntry.getValue().test(i)){
                    forRemove = true;
                    break;
                }
            }
            if(!forRemove) {
                System.out.print(gems[i] + " ");
            }
        }
    }

    private static Predicate<Integer> makePredicate(String[] input) {
        int par = Integer.parseInt(input[2]);
        switch (input[1].toUpperCase()) {
            case "SUM LEFT":
                return x ->
                    ((x > 0) ? gems[x - 1] : 0) + gems[x] == par;
            case "SUM RIGHT":
                return x ->
                        (x < gems.length - 1 ? gems[x + 1] : 0) + gems[x] == par;
            default:
                return x -> gems[x] +
                        (x > 0 ? gems[x - 1] : 0) +
                        (x < gems.length - 1 ? gems[x + 1] : 0) == par;
        }
    }
}