import java.util.Scanner;

public class f_CompareCharArrays {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);

        char[] arr1 = scann.nextLine().replace(" ", "").toCharArray();
        char[] arr2 = scann.nextLine().replace(" ", "").toCharArray();

        char[] smaller = null;
        char[] larger = null;

        // comparing char by char for all characters of the shorter array
        for (int i = 0; i < Math.min(arr1.length, arr2.length); i++) {
            if(arr1[i] != arr2[i]) {
                if ( arr1[i] < arr2[i]) {
                    smaller = arr1;
                    larger = arr2;
                    break;
                } else {
                    smaller = arr2;
                    larger = arr1;
                    break;
                }
            }
        }

        // comparing length if all chars are equal
        if (smaller == null) {
            if (arr1.length <= arr2.length) {
                smaller = arr1;
                larger = arr2;
            } else {
                smaller = arr2;
                larger = arr1;
            }
        }

        System.out.println(smaller);
        System.out.println(larger);
    }
}
