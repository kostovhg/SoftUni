import java.util.Scanner;

public class p03_NMS {
    private static StringBuilder input = new StringBuilder();
    private static StringBuilder output = new StringBuilder();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        while(!"---NMS SEND---".equals(line = scan.nextLine())){
            input.append(line);
        }
        String delim = scan.nextLine();
        output.append(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            if(Character.toLowerCase(input.charAt(i - 1)) > Character.toLowerCase(input.charAt(i))){
                output.append(delim);
            }
            output.append(input.charAt(i));
        }
        System.out.println(output);
    }
}
