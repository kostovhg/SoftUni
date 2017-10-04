import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class p05_Palindromes {

    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        String[] input = scann.nextLine().split("[\\ ,\\.\\?\\! ]+");

        Set<String> palindromes = new TreeSet<>();

        for (String str :
                input) {
            if (IsPalindrome(str)) {
                palindromes.add(str);
            }
        }
        System.out.println(palindromes);
    }

    private static boolean IsPalindrome(String str) {
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }
}