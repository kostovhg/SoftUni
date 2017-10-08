import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class p02_MaximumElement {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> max = new ArrayDeque<>();
        max.push(Integer.MIN_VALUE);
        int comCount = Integer.parseInt(scann.nextLine());
        String[] comm;
        for (int i = 0; i < comCount; i++) {
            comm = scann.nextLine().split("\\s+");
            switch (comm[0]){
                case "1":
                    int num = Integer.parseInt(comm[1]);
                    stack.push(Integer.parseInt(comm[1]));
                    if (num > max.peek()) max.push(num);
                    break;
                case "2":
                    if(stack.pop() == max.peek()) max.pop();
                    break;
                case "3":
                    System.out.println(max.peek());
                    break;
            }
        }
    }
}