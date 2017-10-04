import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p14_SumOfAllValues {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pattern inputPattern = Pattern.compile("^(?<start>[a-zA-Z_]+).+?(?<end>[a-zA-Z_]+)$");

        String inputKeys = scanner.nextLine();
        String inputText = scanner.nextLine();

        List<String> keys = new ArrayList<>();
        Matcher matchKeys = inputPattern.matcher(inputKeys);
        while(matchKeys.find()){
                keys.add(matchKeys.group("start"));
                keys.add(matchKeys.group("end"));
        }
        if(keys.size() < 2) {
            System.out.println("<p>A key is missing</p>");
            return;
        }

        Pattern textPattern = Pattern
                .compile("(?<=" + keys.get(0)+ ")(\\d+\\.?\\d+)(?=" + keys.get(1) +")");
        boolean isInt = true;
        double doubleSum = 0;
        Matcher matchNums = textPattern.matcher(inputText);
        while(matchNums.find()){
                try{
                    doubleSum += Double.parseDouble(matchNums.group());

                } catch(Exception e){

                    continue;
                }

        }
        if(doubleSum == 0 ){
            System.out.println("<p>The total value is: <em>nothing</em></p>");
        }
        else if(doubleSum % 1 == 0){
            System.out.printf("<p>The total value is: <em>%.0f</em></p>", doubleSum);
        } else {
            System.out.printf("<p>The total value is: <em>%.2f</em></p>", doubleSum);
        }
    }
}