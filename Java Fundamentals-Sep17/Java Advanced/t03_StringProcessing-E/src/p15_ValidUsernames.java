import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p15_ValidUsernames {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(?<=[\\s\\\\/()]|^)[a-zA-Z_]\\w{2,24}(?=[\\s\\\\/()]+|$)");
        String input = scanner.nextLine();

        List<String> validNames = new ArrayList<>();

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            validNames.add(matcher.group());
        }

        int maxSum = 0;
        int maxIndex = 0;
        for (int i = 0; i < validNames.size() - 1; i++) {
            int tempSum = validNames.get(i + 1).length() + validNames.get(i).length();
            if(tempSum > maxSum){
                maxSum = tempSum;
                maxIndex = i;
            }
        }
        for (int i = maxIndex; i <= maxIndex + 1; i++) {
            System.out.println(validNames.get(i));
        }
    }
}