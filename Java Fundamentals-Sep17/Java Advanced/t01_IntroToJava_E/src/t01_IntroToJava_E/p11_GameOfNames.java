package t01_IntroToJava_E;

import java.util.Scanner;

public class p11_GameOfNames {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        /*
        From list with players calculate their points as follow:
        we have initial points from which we proceed each ASCII code of
        their characters and
        - add the code if even
        - substract if it is odd
        Print the player with highest score (if two are equal, the first one)
         */
        int pCount = Integer.parseInt(input.nextLine());
        String winner = "";
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < pCount; i++) {
            String player = input.nextLine();
            int points = Integer.parseInt(input.next());
            for (char ch:
                 player.toCharArray()) {
                points += ((ch & 1) == 0) ? ch : -(ch);
            }
            if (points > maxScore) {
                maxScore = points;
                winner = player;
            }
            input.nextLine();
        }
        System.out.printf("The winner is %s - %d points", winner, maxScore);
    }
}