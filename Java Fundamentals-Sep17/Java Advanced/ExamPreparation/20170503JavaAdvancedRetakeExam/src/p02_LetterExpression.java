import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p02_LetterExpression {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder string = new StringBuilder();
        string.append(reader.readLine());
        String regex = "(?<num>(\\d+)|^)(?<op>[^0-9]+|$)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        Double result = 0d;
        boolean plus = true;
        while(matcher.find()){
            /*System.out.println("full match " + matcher.group(0));
            System.out.println("num is " + matcher.group("num"));
            System.out.println("operator is " + (matcher.group("op").length() % 2 == 0 ? "+" : "-"));*/
            Double num = 0d;
            if(!matcher.group("num").isEmpty()){
                num = Double.parseDouble(matcher.group("num"));
            }
            if(plus){
                result += num;
            } else {
                result -= num;
            }
            plus = matcher.group("op").length() % 2 == 0;
        }
        DecimalFormat myFormat = new DecimalFormat("0.#######");
        System.out.println(myFormat.format(result));
    }
}
