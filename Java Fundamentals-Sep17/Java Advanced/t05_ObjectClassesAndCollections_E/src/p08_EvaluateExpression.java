import java.util.ArrayDeque;
import java.util.Scanner;

public class p08_EvaluateExpression {
    private static ArrayDeque<String> expr = new ArrayDeque<>();
    private static ArrayDeque<Double> result = new ArrayDeque<>();
    private static ArrayDeque<String> oper = new ArrayDeque<>();
    private static final String OP = "+*)-/";

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        String[] tokens = scann.nextLine().split("\\s");

        for (String token :
                tokens) {
            toPostfix(token);
        }
        while(!oper.isEmpty()){
            expr.add(oper.poll());
        }
        evaluateExpression();
        System.out.printf("%.2f%n", result.pop());
    }

    private static void evaluateExpression() {

        while(expr.size() > 0) {
            String str = expr.poll();
            if(OP.contains(str)){
                Double second = result.pop();
                Double first = result.remove();
                switch (str){
                    case "+":
                        result.push(first + second);
                        break;
                    case "-":
                        result.push(first - second);
                        break;
                    case "*":
                        result.push(first * second);
                        break;
                    case "/":
                        result.push(first / second);
                        break;
                }
            } else {
                result.push(Double.parseDouble(str));
            }
        }
    }

    private static void toPostfix(String input){
        if(input.equals("(")){
            oper.push(input);
        } else if(input.equals(")")) {
            while (!oper.isEmpty() && !oper.peek().equals("(")) {
                expr.add(oper.pop());
            }
            if(!oper.isEmpty()) oper.pop();
        } else if(!OP.contains(input) ) {
            expr.add(input);
        } else {
            if(!oper.isEmpty()) {
                while (!oper.isEmpty() && OP.indexOf(input) % 3 <= OP.indexOf(oper.peek()) % 3) {
                    expr.add(oper.pop());
                }
            }
            oper.push(input);
        }
    }
}