import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class p01_HandScore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cards = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        // totalScore = 0
        // current count = 0;
        // with looping add count for each loop
        // if suit is different null the count and multiply the temp sum by the count
        // at the end of loop sum

        Long totalScore = 0L;
        int count = 1;
        Long tempScore = cardPower(cards.get(0));
        Character previousSuit = cards.get(0).charAt(cards.get(0).length() - 1);
        for (int i = 1; i < cards.size(); i++) {
            if(cards.get(i).charAt(cards.get(i).length() - 1) == previousSuit){
                tempScore += cardPower(cards.get(i));
                count++;
            } else {
                totalScore += tempScore * count;
                tempScore = cardPower(cards.get(i));
                count = 1;
                previousSuit = cards.get(i).charAt(cards.get(i).length() - 1);
            }
            if(i == cards.size() - 1) {
                totalScore += tempScore * count;
            }
        }

        System.out.println(totalScore);
    }

    private static Long cardPower(String s) {
        Long power = 0L;
        String p = s.substring(0, s.length() -1);
        try {
            power = Long.valueOf(p);
        } catch (NumberFormatException e) {
            switch (p){
                case "J": power = 12L; break;
                case "Q": power = 13L; break;
                case "K": power = 14L; break;
                case "A": power = 15L; break;
            }
        }
        return power;
    }

}
