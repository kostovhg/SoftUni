import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class p01_MachingBrackets {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Deque<Integer> stack = new ArrayDeque();

        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '('){
                stack.push(i);
            } else if(input.charAt(i) == ')'){
                int start = stack.pop();
                String cont = input.substring(start, i + 1);
                System.out.println(cont);
            }
        }
    }
}