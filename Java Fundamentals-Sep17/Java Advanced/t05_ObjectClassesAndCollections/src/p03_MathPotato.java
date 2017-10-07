import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

public class p03_MathPotato {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Deque<String> names = new ArrayDeque<>();

        Collections.addAll(names, scann.nextLine().split("\\s+"));
        int count = Integer.parseInt(scann.nextLine());
        int cycles = 1;
        while (names.size() > 1){
            for (int i = 1; i < count; i++) {
                names.offer(names.remove());
            }
            if(isPrime(cycles)){
                System.out.println("Prime " + names.peek());
            } else {
                System.out.println("Removed " + names.poll());
            }
            cycles++;
        }
        System.out.println("Last is " + names.remove());

    }

    private static boolean isPrime(int cycles) {
        if(cycles <= 1) return false;
        for(int i = 2; i <= Math.sqrt(cycles); i++){
            if(cycles % i == 0) return false;
        }
        return true;
    }
}