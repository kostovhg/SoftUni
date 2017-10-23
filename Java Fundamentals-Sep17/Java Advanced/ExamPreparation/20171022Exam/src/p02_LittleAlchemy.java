import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p02_LittleAlchemy {

    public static void main(String[] args) {
        /*
        "Apply acid {n}" – Prof. X applies n doses of acid on n number of stones and moves each at the end.  If there are no stones at the table, ignore the command.
"Air Leak {m}" – there is a leak in the storage, the last piece gets damaged and must be treated with n more doses to turn into gold again. If the storage is empty, ignore the command.
"Revision"
         */

        Scanner scanner = new Scanner(System.in);

        Deque<Integer> stones = new ArrayDeque<>();
        Deque<Integer> storage = new ArrayDeque<>();

        String[] input = scanner.nextLine().split("\\s+");
        for (int i = 0; i < input.length; i++) {
            stones.add(Integer.valueOf(input[i]));
        }

        String[] command;
        while(!"Revision".equals((command = scanner.nextLine().split("\\s+"))[0])){
            int count = Integer.parseInt(command[2]);
            switch  (command[0].toLowerCase()){
                case "apply":
                    for (int i = 0; i < count && stones.size() > 0; i++) {
                        int stone = stones.poll() - 1;
                        if(stone > 0) stones.add(stone);
                        else storage.push(stone);
                    }
                    break;
                case "air":
                    if(storage.size() > 0){
                        storage.pop();
                        stones.add(count);
                    }
                    break;
            }

        }

        while (stones.size() > 0) {
            System.out.print(stones.poll() + " ");
        }

        System.out.println();
        System.out.println(storage.size());
    }
}
