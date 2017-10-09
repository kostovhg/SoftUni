import java.util.*;

public class p07_InfixToPostfix {
    private static ArrayDeque<String> stack = new ArrayDeque<>();
    private static ArrayDeque<String> queue = new ArrayDeque<>();
    // This string is arranged in such a way that character positions
    // mode of 3 to give each operator weight as follow:
    // index 0 and 3 -> wight 0, 1 and 4 -> weight 1, index 2 -> weight 2
    // or -, /, ), +, *
    private static String OPERANDS = "-/)+*";

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String[] input = scann.nextLine().split("\\s");

        // while there are characters in input
        // read the character and
        for(String in : input) {
            // put opening brackets in the stack
            if(in.equals("(")){
                stack.push(in);
            } else if(in.equals(")")) {
                // take out to the queue all (if any) operators from the stack till
                // opening brackets
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    queue.add(stack.pop());
                }
                // if we have opening bracket in the head of the stack
                // it is time to remove it.
                if(!stack.isEmpty()) stack.pop();
            } else if(!OPERANDS.contains(in) ) {
                // if current input is not a operator or brackets
                // add it to the queue
                queue.add(in);
            } else {
                // if we have operator, try to find all operators in the stack
                // with higher or equal weight to insert them to the queue before the
                // current one.
                if(!stack.isEmpty()) {
                    // If the weight of the operator in the stack is higher than
                    // the weight of current operator, put them in the queue
                    while (!stack.isEmpty() && OPERANDS.indexOf(in) % 3 <= OPERANDS.indexOf(stack.peek()) % 3) {
                        queue.add(stack.pop());
                    }
                }
                // if in the stack the operators are with lower precendence
                // push current operator to the stack;
                stack.push(in);
            }

        }
        // until there is something in the stack, take it out to the queue
        while(!stack.isEmpty()){
            queue.add(stack.poll());
        }
        // print the queue
        while (!queue.isEmpty()){
            System.out.print(queue.poll() + " ");
        }

    }
}