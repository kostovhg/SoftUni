import java.util.*;
import java.util.stream.Collectors;

public class p02_Earthquake {
    private static Deque<Deque<Integer>> seismicities = new ArrayDeque<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Integer seismicity;
    private static StringBuilder output = new StringBuilder();
    public static void main(String[] args) {
        int count = Integer.parseInt(scanner.nextLine());
        String regex = "\\s+";

        for (int i = 0; i < count; i++) {
            Deque<Integer> waves = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(scanner.nextLine(), " ");
            while(st.hasMoreTokens()){
                waves.add(Integer.valueOf(st.nextToken()));
            }
            seismicities.add(waves);
        }
        count = 0;
        while(seismicities.size() > 0){
            Deque<Integer> currentWaves = seismicities.poll();
            Integer seismicity = currentWaves.pop();
            output.append(seismicity + " ");
            count++;
            if(currentWaves.size() < 1) continue;
            while (seismicity >= currentWaves.peek()){
                currentWaves.pop();
                if(currentWaves.size() < 1) break;
            }
            if(currentWaves.size() > 0) seismicities.add(currentWaves);
        }

        System.out.println(count);
        System.out.println(output);
    }
}
