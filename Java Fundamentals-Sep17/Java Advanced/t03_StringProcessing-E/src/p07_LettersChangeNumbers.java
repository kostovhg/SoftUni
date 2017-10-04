import java.util.Scanner;

public class p07_LettersChangeNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");

        double result = 0;

        for (String str :
                input) {
            result += CalcValue(str);
        }

        System.out.printf("%.2f", result);
    }

    private static double CalcValue(String str) {
        char first = str.charAt(0);
        char last = str.charAt(str.length() - 1);
        double num = Double.parseDouble(str.substring(1,str.length() - 1));
        int firstPos = Character.toLowerCase(first) - 'a' + 1;
        int secondPos = Character.toLowerCase(last) - 'a' + 1;

        if(first >= 'A' && first <= 'Z'){
            num /= firstPos;
        } else {
            num *= firstPos;
        }
        if(Character.isUpperCase(last)){
            num -= secondPos;
        } else {
            num += secondPos;
        }
        return num;
    }
}