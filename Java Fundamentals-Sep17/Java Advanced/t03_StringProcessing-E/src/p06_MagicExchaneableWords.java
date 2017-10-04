import java.util.HashMap;
import java.util.Scanner;

public class p06_MagicExchaneableWords {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        String[] input = scann.nextLine().split("\\s+");
        char[] shorter = input[(input[0].length() > input[1].length()) ? 1 : 0].toCharArray();
        char[] longer = input[(input[0].length() <= input[1].length()) ? 1 : 0].toCharArray();

        boolean output = true;
        HashMap<Character, Character> mapper = new HashMap<Character, Character>();
        for (int i = 0; i < shorter.length; i++) {
            if(!mapper.containsKey(shorter[i])){
                if(mapper.containsValue(longer[i])){
                    output = false;
                    break;
                } else {
                    mapper.put(shorter[i], longer[i]);
                }
            } else {
                if(longer[i] != mapper.get(shorter[i])){
                    output = false;
                    return;
                }
            }
        }

        for (int i = shorter.length; i < longer.length; i++) {
            if(!mapper.containsValue(longer[i])){
                output = false;
                break;
            }
        }

        System.out.println(output);
    }
}