import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p03_Ascent {
    private static Pattern pattern = Pattern.compile("([,_])([a-zA-Z]+)([0-9])");
    private static Map<String, String> recorded = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> text = new ArrayList<>();

    public static void main(String[] args) {
        String line;
        // fill the list
        while(!"Ascend".equals(line = scanner.nextLine())) {
            text.add(line);
        }
        // iterate list
        for(int i = 0; i < text.size(); i++){
            // replace previously find codes before decoding current line
            for (String e : recorded.keySet() ) {
                text.set(i, text.get(i).replace(e, recorded.get(e)));
            }
            // search pattern in current line
            Matcher m = pattern.matcher(text.get(i));
            // until we have codes
            while(m.find()){
                // take the original and decoded version
                String original = m.group(0);
                String decoded = decode(m.group(1), m.group(2), m.group(3));
                // save decoding if it doesn't exist
                recorded.putIfAbsent(m.group(), decoded);
                // change whole line
                text.set(i, text.get(i).replace(original, decoded));
            }
            System.out.println(text.get(i));
        }
    }

    private static String decode(String op, String msg, String digit){
        int dif = Integer.parseInt(digit);
        char[] array = msg.toCharArray();
        if(op.equals("_")){
            for (int i = 0; i < msg.length(); i++) {
                array[i] -= dif;
            }
        }else {
            for (int i = 0; i < msg.length(); i++) {
                array[i] += dif;
            }
        }
        return String.valueOf(array);
    }
}
