import java.util.Scanner;

public class p08_MultiplyBigNumber {
    static int times    ;
    static int carry;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // ? trim leading zeroes from the input
        StringBuilder strNum = new StringBuilder(scan.nextLine()
                .replaceFirst ("^0*", ""));
        // ? leading zeroes will disappear magically
        times = Integer.parseInt(scan.nextLine());
        if(times > 1){
            // use recursion method to loop from right to left
            multiplyString(strNum, strNum.length() - 1);
        }  else if ( times  == 0 || strNum.equals("")) {
            // clear and change the StringBuilder object.
            strNum.setLength(1);
            strNum.trimToSize();
            strNum.setCharAt(0, '0');
        }

        System.out.println(strNum);
    }

    private static void multiplyString(StringBuilder strNum, int curr) {
        // bottom, stop when numbers finish
        if(curr < 0){
            // if we have some carry from previous recursion
            if( carry > 0) {
                // insert it in the beginning of string
                strNum.insert(0, Character.forDigit(carry, 10));
                // null it for next recursion;
                carry = 0;
            }
            // exit current recursion
            return;
        }
        // calculate the result of multiplication with
        // digit at index 'curr' by multiplicand
        int res = times * (strNum.charAt(curr) - 48);
        // check if we have bigger than 10 value of addition
        // between last carry and current ones of the result
        if(res % 10 + carry < 10){
            // if not, then just sum the ones with the carry and set the result
            // as char at index 'curr'
            strNum.setCharAt(curr, Character.forDigit((res % 10 + carry ), 10));
            // calculate new carry to be added on next recursion
            carry = res / 10;
        } else {
            // if we do, then take the ones of sum between current result and the carry
            // put the digit of ones at index 'curr'
            strNum.setCharAt(curr, Character.forDigit((res + carry) % 10, 10));
            // and set the value of next carry as digit of tenths of the sum
            carry = (res + carry) / 10;
        }

        // go to the leftmost character
        multiplyString(strNum, curr - 1);
    }
}