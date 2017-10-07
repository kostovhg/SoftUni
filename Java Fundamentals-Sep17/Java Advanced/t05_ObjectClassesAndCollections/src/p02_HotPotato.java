import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

public class p02_HotPotato {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Deque<String> names = new ArrayDeque<>();

        Collections.addAll(names, scann.nextLine().split("\\s+"));
        int count = Integer.parseInt(scann.nextLine());
        while (names.size() > 1){
            for (int i = 1; i < count; i++) {
                names.add(names.poll());
            }
            System.out.println("Removed " + names.poll());
        }
        System.out.println("Last is " + names.poll());


    }
}