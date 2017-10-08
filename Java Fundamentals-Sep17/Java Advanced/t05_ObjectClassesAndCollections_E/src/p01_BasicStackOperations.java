import java.util.*;

public class p01_BasicStackOperations {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque();
        int[] coms = Arrays
                .stream(scann.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < coms[0]; i++) {
             stack.push(scann.nextInt());
        }
        for (int i = 0; i < coms[1]; i++) {
            stack.pop();
        }
        if(stack.contains(coms[2])) {
            System.out.println("true");
        } else if (stack.isEmpty()) {
            System.out.println("0");
        } else {
            int min = Integer.MAX_VALUE;
            while(!stack.isEmpty())
            {
                int curr = stack.pop();
                if(curr < min) min = curr;
            }
            System.out.println(min);
        }

    }
}