import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p_ChangeToUppercase {
    public static void main(String[] args) throws IOException {

        // shorter for codding
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final String regex = "(<upcase>(.+?)<\\/upcase>)";
        String input = reader.readLine();
        /*
        String iin = input;
        */

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);

        while(matcher.find()){
            input = input.replace(matcher.group(1), matcher.group(2).toUpperCase());
        }

        System.out.println(input);
        /*
        System.out.println(toUpper(iin, "<upcase>", "</upcase>"));
        */
    }

    /*************************************************************
     * Longer variant
     * The program do not support nested tags
     * @param input - the text to be modified
     * @param startBlock - begin tag
     * @param endBlock - end tag
     * @return - String output
     */

    private static String toUpper(String input, String startBlock, String endBlock){
        StringBuilder result = new StringBuilder();
        int startLength = startBlock.length();
        int endLength = endBlock.length();

        while (true) {
            if (!input.contains(startBlock) ||
                    input.indexOf(endBlock) <= input.indexOf(startBlock)){
                result.append(input);
                break;
            }
            // append substring from beginning of remaining text to startBlock
            // append substring between startBlock and endBlock and apply to this interval .toUpperCase
            result
                    .append(input.substring(0,
                            input.indexOf(startBlock)))
                    .append(input.substring(
                            input.indexOf(startBlock) + startLength,
                            input.indexOf(endBlock)
                    ).toUpperCase());
            // remove already transformed text from the beginning of what is left from original text
            // to the end of endBlock
            input = input.substring(input.indexOf(endBlock) + endLength);
        }
        return result.toString();
    }
}
