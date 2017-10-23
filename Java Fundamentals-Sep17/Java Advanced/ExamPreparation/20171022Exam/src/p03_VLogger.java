import java.util.*;

public class p03_VLogger {

    private static Map<String, TreeSet<String>> folowers = new HashMap<>(); // user - set of followers
    private static Map<String, TreeSet<String>> follow = new HashMap<>(); // user - list of users he follows
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        while(!"statistics".equalsIgnoreCase(input = scanner.nextLine())){
            String[] tokens = input.split("\\s+");
            switch (tokens[1]){
                case "joined":
                    if (!folowers.containsKey(tokens[0])){
                        folowers.put(tokens[0], new TreeSet<>());
                        follow.put(tokens[0], new TreeSet<>());
                    }
                    break;
                case "followed":
                    String vloger1 = tokens[0];
                    String vloger2 = tokens[2];
                    if(vloger1.equals(vloger2)) continue;
                    if(folowers.containsKey(vloger1) && folowers.containsKey(vloger2) ) {
                        folowers.get(vloger2).add(vloger1);
                        follow.get(vloger1).add(vloger2);
                    }
                    break;
            }
        }
        System.out.printf("The V-Logger has a total of %d vloggers in its logs.%n", folowers.size());
        final int[] counter = {1};
        folowers.entrySet().stream().sorted( (x1, x2) -> {
            if( x1.getValue().size() == x2.getValue().size())
                return follow.get(x1.getKey()).size() - follow.get(x2.getKey()).size();
            else
                return x2.getValue().size() - x1.getValue().size();
        }).forEach( l -> {
            System.out.printf("%d. %s : %d followers, %d following%n",
                    counter[0],
                    l.getKey(),
                    l.getValue().size(),
                    follow.get(l.getKey()).size());
            counter[0]++;
        if (counter[0] == 2){
            for (String folower :
                    l.getValue()) {
                System.out.printf("*  %s%n", folower);
            }
        }
        });
    }
}
