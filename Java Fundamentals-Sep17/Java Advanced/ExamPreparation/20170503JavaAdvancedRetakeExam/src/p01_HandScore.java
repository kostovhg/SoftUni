import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class p01_HandScore {
    static class Card{
        String face;
        Character suit;
        int power;
        private Card(String card){
            face = card.substring(0, card.length() - 1);
            suit = card.charAt(card.length() - 1);
            try{
                this.power = Integer.parseInt(face);
            } catch (NumberFormatException cce){
                switch(face){
                    case "J": power = 12; break;
                    case "Q": power = 13; break;
                    case "K": power = 14; break;
                    case "A": power = 15; break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputCards = reader.readLine().split("\\s+");
        long result = 0;
        ArrayList<Card> cards = Arrays.stream(inputCards)
                .map(Card::new).collect(Collectors.toCollection(ArrayList::new));

        Character previousSuit = cards.get(0).suit;
        long suitSum = cards.get(0).power;
        int count = 1;
        for (int i = 1; i < cards.size(); i++) {
            if(cards.get(i).suit == previousSuit){
                suitSum += cards.get(i).power;
                count++;
                if(i == cards.size() - 1){
                    result += suitSum*count;
                    break;
                }
            } else {
                result += suitSum * count;
                suitSum = cards.get(i).power;
                count = 1;
                if(i == cards.size() - 1){
                    result += suitSum ;
                    break;
                }
            }
            previousSuit = cards.get(i).suit;
        }
        System.out.println(result);
    }

}
