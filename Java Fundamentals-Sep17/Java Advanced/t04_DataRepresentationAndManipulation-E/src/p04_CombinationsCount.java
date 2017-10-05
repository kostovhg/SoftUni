import java.util.Scanner;

public class   p04_CombinationsCount {



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int k = Integer.parseInt(scan.nextLine());

        System.out.println(binom(n, k));

    }

    private static long binom(int n, int k) {
        if(k == n || k == 0){
            return 1;
        }
        else return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}