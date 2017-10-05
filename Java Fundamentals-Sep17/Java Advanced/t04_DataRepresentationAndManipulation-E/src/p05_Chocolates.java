import java.util.Arrays;
import java.util.Scanner;

public class p05_Chocolates {
    static int[] chocos;
    static int len;
    static int students;

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        len = Integer.parseInt(scann.nextLine());
        chocos = Arrays.stream(scann.nextLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        if(len != chocos.length) len = chocos.length;
        students = Integer.parseInt(scann.nextLine());
        long minDif = Long.MAX_VALUE;
        
        SortPackages(chocos);

        for (int i = 0; i <= len - students; i++) {
            long temp = chocos[i + students - 1] - chocos[i];
            if(temp < minDif) minDif = temp;
        }
        System.out.printf("Min Difference is %d.",minDif);
    }

    private static void SortPackages(int[] arr) {
        /*
        Merge sort taken from
        https://stackoverflow.com/questions/13446282/writing-a-recursive-sorting-algorithm-of-an-array-of-integers
         */

        // Bottom
        if(arr.length <= 1) return;

        // Pre action: split the array
        int[] a = new int[arr.length / 2];
        int[] b = new int[arr.length - a.length];
        for (int i = 0; i < arr.length; i++) {
            if(i < a.length) a[i] = arr[i];
            else b[i - a.length] = arr[i];
        }

        // Recursively sort both arrays
        SortPackages(a);
        SortPackages(b);

        // Post action: Merge halves
        int ai = 0;
        int bi = 0;
        while(ai + bi < arr.length){

            if(bi >= b.length
                    || (ai < a.length && a[ai] < b[bi])){
                arr[ai + bi] = a[ai];
                ai++;
            } else {
                arr[ai + bi] = b[bi];
                bi++;
            }
        }
    }
}