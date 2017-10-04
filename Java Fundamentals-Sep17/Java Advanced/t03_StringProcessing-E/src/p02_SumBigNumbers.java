import java.util.Scanner;

public class p02_SumBigNumbers {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        String strFirst = scan.nextLine();
        String strSecond = scan.nextLine();
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        if (strFirst.length() < strSecond.length()){
            first.append(strSecond).reverse();
            second.append(strFirst).reverse();
        }
        else {
            first.append(strFirst).reverse();
            second.append(strSecond).reverse();
        }

        StringBuilder result = new StringBuilder();
        int rem = 0;
        for (int i = 0; i < first.length(); i++) {
            byte intFirst = Byte.parseByte(first.substring(i, i + 1));
            byte intSecond = (i < second.length() ) ? Byte.parseByte(second.substring(i, i + 1)): 0;
            int res = rem + intFirst + intSecond;
            result.append(res % 10);
            rem = res / 10;
        }
        if(rem > 0) result.append("1");
        // and remove leading zeroes
        for (int i = result.length() - 1; i > 1; i--) {
            if(result.charAt(i) == '0') result.deleteCharAt(i);
            else break;
        }
        System.out.println(result.reverse());


    }
}
