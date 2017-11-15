package p04_Telephony;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split(" ");
        String[] sites = scanner.nextLine().split(" ");

        Smarthpone phone = new Smarthpone();
        phone.addNumberToCall(numbers);
        phone.addSiteToVisit(sites);

        phone.call();
        phone.browse();
    }
}
