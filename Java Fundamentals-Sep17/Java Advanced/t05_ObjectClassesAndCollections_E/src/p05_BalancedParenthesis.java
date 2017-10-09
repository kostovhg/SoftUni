import java.util.ArrayDeque;
import java.util.Scanner;

public class p05_BalancedParenthesis {
    private static ArrayDeque<Character> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String parentheses = "{}[]()";

        boolean balanced = true;
        char[] line = scann.nextLine().toCharArray();
        for (char ch :
                line) {
            int index = parentheses.indexOf(ch);
            if(index % 2 == 0){
                stack.push(ch);
            } else if (stack.size() == 0
                    || parentheses.indexOf(stack.peek()) != index - 1) {
                balanced = false;
                break;
            } else {
                stack.pop();
            }
        }
        if(stack.size() > 0 || !balanced){
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }

    }
}