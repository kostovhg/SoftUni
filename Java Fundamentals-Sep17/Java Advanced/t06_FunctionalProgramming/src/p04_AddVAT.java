import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class p04_AddVAT {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("###0.00", otherSymbols);
        List<Double> prices = Arrays.stream(sc.nextLine().split(", "))
                .map(Double::parseDouble).collect(Collectors.toList());

        UnaryOperator<Double> uOp = (x) -> x * 1.2;

        System.out.println("Prices with VAT:");

        for(Double price : prices){
            System.out.println(df.format(uOp.apply(price)));
        }

    }
}