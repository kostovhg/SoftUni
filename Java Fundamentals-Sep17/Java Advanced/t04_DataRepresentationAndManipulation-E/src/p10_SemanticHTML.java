import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p10_SemanticHTML {
    static List<String> htmlCode = new ArrayList<>();

    public static void main(String[] args) {
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        Pattern start = Pattern
                .compile("^( *)<div\\s*.*((?:id|class)\\s*=\\s*\"(main|header|nav|article|section|aside|footer)\")\\s*.*>");
        Pattern end = Pattern.compile("^( *)(</div> +<!-- *(main|header|nav|article|section|aside|footer) *-->)");
        String input = stdin.nextLine();
        while(!"END".equals(input)){
            Matcher s = start.matcher(input);
            Matcher e = end.matcher(input);
            if(s.find()){
                input = input.replace(s.group(2), "");
                input = input.replace("<div", "<" + s.group(3));
                input = input.replaceAll("(?<=\\S) +(?=\\S)", " ");
                input = input.replaceAll(" >", ">");
            } else if(e.find()){
                input = input.replace(e.group(2),"</" + e.group(3) + ">");
            }
            htmlCode.add(input);
            input = stdin.nextLine();
        }
        for (String line :
                htmlCode) {
            System.out.println(line);
        }
    }

}