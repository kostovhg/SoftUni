import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03_Ascent {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            Pattern pattern = Pattern.compile("(,|_)([a-zA-Z]+)([0-9])");
            LinkedHashMap<String, String> memos = new LinkedHashMap<>();

            ArrayList<String> text = new ArrayList<>();
            String input;
            // fill the array
            while(!"Ascend".equalsIgnoreCase(input = reader.readLine())){
                text.add(input);
            }

            for (int i = 0; i < text.size(); i++) {
                for (String memo : memos.keySet()) {
                    text.set(i, text.get(i).replaceAll(memo, memos.get(memo)));
                }

                Matcher matcher = pattern.matcher(text.get(i));
                while(matcher.find()){
                    String original = matcher.group(0);
                    String newer = decodeOriginal(
                            matcher.group(0).charAt(0),
                            matcher.group(2).toCharArray(),
                            matcher.group(3));
                    memos.putIfAbsent(original, newer);
                    text.set(i, text.get(i).replaceAll(original, newer));
                }
                System.out.println(text.get(i));
            }

            
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private static String decodeOriginal(char ch, char[] code, String op) {
        int oper = Character.getNumericValue(op.charAt(0));
        if(ch == ','){
            for (int i = 0; i < code.length; i++) {
                if(Character.isLetter(code[i])) {
                    code[i] = (char)(code[i] + oper);
                }
            }
        } else {
            for (int i = 0; i < code.length; i++) {
                if(Character.isLetter(code[i])) {
                    code[i] = (char)(code[i] - oper);
                }
            }
        }
        return String.valueOf(code);
    }

    public static void replaceAll(StringBuilder builder, String from, String to)
    {
        int index = builder.indexOf(from);
        while (index != -1)
        {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }
}
