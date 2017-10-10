import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class p09_TheStockSpanProblem {
    private static ArrayDeque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        BufferedReader scann = null;
        try{
            scann = new BufferedReader(new InputStreamReader(System.in));

            int days = Integer.parseInt(scann.readLine());
            double[] prices = new double[days];
            StringBuilder output = new StringBuilder("1").append("\n");
            prices[0] = Double.parseDouble(scann.readLine());
            stack.push(0);
            for (int i = 1; i < days; i++) {
                prices[i] = Double.parseDouble(scann.readLine());
                while(!stack.isEmpty() && prices[stack.peek()] <= prices[i])
                    stack.pop();

                output.append((stack.isEmpty()) ? (i + 1) : (i - stack.peek())).append("\n");
                stack.push(i);
            }
            System.out.println(output);
        } catch (IOException e){
            System.out.println("io error");
            e.printStackTrace();
        } finally {
            try{
                scann.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}