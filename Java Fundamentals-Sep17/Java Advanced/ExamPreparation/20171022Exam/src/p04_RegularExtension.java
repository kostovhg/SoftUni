import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p04_RegularExtension {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        String pattern;
        while (!"Print".equals(pattern = reader.readLine())){
            // check for % if doesn't exist
            if(!pattern.contains("%")){
                // find index of pattern
                int ind = text.indexOf(pattern);
                while (ind != -1){
                    // until we have matches replace them with inverted pattern
                    text = text.replace(pattern, new StringBuilder(pattern).reverse());
                    ind = text.indexOf(pattern);
                }
            }
            else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pattern.length(); i++) {
                    String part = String.valueOf(pattern.charAt(i));
                    if(part.equalsIgnoreCase("%"))
                        sb.append("\\S*");
                    else
                        sb.append(Pattern.quote(part));

                }

                Pattern p = Pattern.compile(sb.toString());
                Matcher m = p.matcher(text);
                while (m.find()){
                    String cap = m.group(0);
                    text = text.replaceAll(Pattern.quote(cap), reverse(cap));
                }
            }
        }


        System.out.println(text);

    }

    private static String reverse(String s){
        StringBuilder res = new StringBuilder();
        for (int i = s.length()-1; i >= 0 ; i--)
            res.append(s.charAt(i));
        return res.toString();
    }
}