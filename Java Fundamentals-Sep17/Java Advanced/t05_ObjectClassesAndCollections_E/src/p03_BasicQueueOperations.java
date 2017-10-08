import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class p03_BasicQueueOperations {
    private static Deque<Integer> queue = new ArrayDeque<>();
    private static Deque<Integer> minStack = new ArrayDeque<>();
    static private int[] readIntArr(String line){
        return Arrays.stream(line.split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        int[] comm = readIntArr(scann.nextLine());
        int[] nums = readIntArr(scann.nextLine());
        minStack.push(Integer.MAX_VALUE);
        for (int i = 0; i < comm[0]; i++) {
            queue.add(nums[i]);
            if(nums[i] < minStack.peek()) minStack.push(nums[i]);
        }
        for (int i = 0; i < comm[1]; i++) {
            if(queue.poll().equals(minStack.peek())) minStack.pop();
        }
        if(queue.contains(comm[2])) System.out.println("true");
        else if(queue.isEmpty()) System.out.println("0");
        else
        {
            System.out.println(minStack.peek());
        }
    }
}