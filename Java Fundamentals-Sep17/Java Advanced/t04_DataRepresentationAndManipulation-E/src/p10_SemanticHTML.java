import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p10_SemanticHTML {
    static List<String> htmlCode = new ArrayList<>();
    static int end;

    public static void main(String[] args) {
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        Pattern start = Pattern
                .compile("^(\\s*)<div\\s*.*((?:id|class)\\s*=\\s*\"(main|header|nav|article|selection|aside|footer)\")\\s*.*>");
        String inputLine = stdin.nextLine();

        while(!inputLine.equals("END")){
            htmlCode.add(inputLine);
            inputLine = stdin.nextLine();
        }

        for (int i = 0; i < htmlCode.size() - 1; i++) {
            Matcher mStart = start.matcher(htmlCode.get(i));
            if(mStart.find()){
                String id = mStart.group(3);
                String space = mStart.group(1);
                Pattern mEnd = Pattern
                        .compile("^" + space +
                                "</div\\s*>\\s*<!--\\s*" +
                                id + "\\s*-->");
                for (int j = i + 1; j < htmlCode.size(); j++) {
                    Matcher m = mEnd.matcher(htmlCode.get(j));
                    if(m.find()){
                        end = j;
                        changeTags(i, j, space, id);
                        break;
                    }
                }
            }
        }
        for (String line :
                htmlCode) {
            System.out.println(line);

        }
    }

    private static void changeTags(int i, int j, String space, String id) {
        String open = htmlCode.get(i)
                .replaceAll("\\s(id|class)\\s*=\\s*\"\\s*" + id + "\\s*\"", "");
        open = open.replaceAll("<div", "<" + id );
        open = (space.length() > 0 ? space.substring(0, space.length() - 1) : "") +
                open.replaceAll("(?<=\\S) +(?=\\S)", " ");
        open = open.replaceAll(" >", ">");
        htmlCode.set(i, open);

        htmlCode.set(j,
                htmlCode.get(j).replaceAll("</div>\\s*<!--\\s*" + id + "\\s*-->", "</"+ id + ">"));

    }

}