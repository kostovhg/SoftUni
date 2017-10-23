import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p04_RegularExtension {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        //String[] theText = (scann.nextLine()).split("\\s+");
        StringBuilder theText = new StringBuilder(scann.nextLine());

        //ArrayList<String> words = new ArrayList<>();
        //words.addAll(Arrays.asList(theText));

/*        ArrayList<String> patterns = new ArrayList<>();*/
        String input;


        while (!"Print".equals(input = scann.nextLine())){
            String rev = "";
            int start = 0;
            //if(input.contains(".")) input = input.replace(".", "\\.");
            if(input.contains("%") || input.contains(".")){
                input = input.replace("%", "[^ ]*");
                input = input.replace(".", "\\.");
            } else {
                /*start = theText.indexOf(input);
                if(start < 0) continue;
                rev = theText.substring(start, start + input.length());
                rev = new StringBuilder(rev).reverse().toString();
                theText = theText.replace(start, start + rev.length(), rev);*/
            }

            Pattern p = Pattern.compile(input);
            //for (int i = 0; i < words.size(); i++) {
            Matcher m = p.matcher(theText);

            while(m.find()){
                rev = m.group();
                rev = new StringBuilder(rev).reverse().toString();
                start = theText.indexOf(m.group());
                theText = theText.replace(start, start + rev.length(), rev);
            }

            /*for (String word : words) {t
                Matcher m = p.matcher(word);
                if(m.find()){
                    String rev = m.group();
                    rev = new StringBuilder(rev).reverse().toString();
                    word = word.replace(m.group(), rev);
                }
            }*/
            //}

        }
        System.out.println(theText.toString());
        /*for (String str :
                words) {
            System.out.print(str + " ");
        }*/
    }
}
