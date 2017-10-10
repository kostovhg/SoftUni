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
            int[] prices = new int[days];
            //int[] spans = new int[days];
            prices[0] = Integer.parseInt(scann.readLine());
            System.out.println(1);
            stack.push(0);
            for (int i = 1; i < days; i++) {
                prices[i] = Integer.parseInt(scann.readLine());
                while(!stack.isEmpty() && prices[stack.peek()] <= prices[i])
                    stack.pop();

                System.out.println((stack.isEmpty()) ? (i + 1) : (i - stack.peek()));
                stack.push(i);
            }
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