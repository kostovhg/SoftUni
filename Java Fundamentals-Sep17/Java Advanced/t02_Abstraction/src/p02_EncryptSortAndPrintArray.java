import java.util.Arrays;
import java.util.Scanner;

public class p02_EncryptSortAndPrintArray {
    static String vowels = "aeiouAEIOU";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int len = Integer.parseInt(input.nextLine());
        String[] sArray = new String[len];
        for (int i = 0; i < len; i++) {
            sArray[i] = input.nextLine();
        }
        long[] numStrArray = new long[len];
        for (int i = 0; i < len; i++) {
            numStrArray[i] = encryptString(sArray[i]);
        }
        Arrays.sort(numStrArray);
        for(long res : numStrArray){
            System.out.println(res);
        }
    }

    private static long encryptString(String s) {
        long result = 0;
        int len = s.length();
        for (char ch :
                s.toCharArray()) {
            if(vowels.indexOf(ch) >= 0){
                result += ch * len;
            } else {
                result += ch / len;
            }
        }
        return result;
    }
}