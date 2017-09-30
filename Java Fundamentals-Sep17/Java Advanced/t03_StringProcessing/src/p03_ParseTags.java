import java.util.Scanner;

public class p03_ParseTags {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Read the input on one line
        String input = sc.nextLine();
        // set the tags
        String up = "<upcase>";
        String down = "</upcase>";
        // find first occurrences of the tags
        int start = input.indexOf(up);
        int end = input.indexOf(down);
        // create counter in case we have more than one substring closed in tags
        int count = 0;
        // Create StringBuilder variable, that will hold the output
        // with initial value of lowercase, if any
        if(start < 0) start = input.length(); // check if we have any opening tag
        StringBuilder output = new StringBuilder(input.substring(0, start));
        // go trough rest of the input
        while (start > -1 && end > -1){
            // if we have more tags, cut what is between them
            output.append(input.substring(start + up.length(), end).toUpperCase());
            count++;
            start = input.indexOf(up, start + count);
            if(start < 0) start = input.length();
            if(end + down.length() > start) break;
            // append what follows the tags until new opening tag
            output.append(input.substring(end + down.length(), start));
            end = input.indexOf(down, end + count);
        }
        System.out.println(output);
    }
}