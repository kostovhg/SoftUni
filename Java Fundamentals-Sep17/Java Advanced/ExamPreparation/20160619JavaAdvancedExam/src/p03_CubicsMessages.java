import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03_CubicsMessages {
    private static Scanner scanner = new Scanner(System.in);
    private static String line;
    private static String msgLength;

    public static void main(String[] args) {

        while(!"Over!".equals(line = scanner.nextLine())){
            int len = Integer.parseInt(scanner.nextLine());
            Matcher matcher = Pattern
                    .compile("^(\\d+)([a-zA-Z]+)([^a-zA-Z]*)$")
                    .matcher(line);
            if(!matcher.find() || len != matcher.group(2).length()) continue;
            System.out.printf("%s == ", matcher.group(2));

            /* This one is slower
            (matcher.group(1) + matcher.group(3)).chars()
                    .map(x -> (char)x)
                    .filter(x -> x >= '0' && x <= '9')
                    .forEach(ch -> {
                        if(ch - '0' >= Integer.valueOf(msgLength)) System.out.print(' ');
                        else System.out.print(matcher.group(2).charAt(ch - '0'));
                    });*/

            // much faster
            Matcher m = Pattern.compile("-?\\d").matcher(matcher.group(1) + matcher.group(3));
            while(m.find()){
                int index = Integer.parseInt(m.group());
                if(index >= 0 && index < len)
                    System.out.print(matcher.group(2).charAt(index));
                else System.out.print(' ');
            }
            System.out.println();
        }
    }
}
