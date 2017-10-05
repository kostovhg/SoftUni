import java.util.Arrays;
import java.util.Scanner;

public class p06_OverlappingIntervals {
    static int[][] intervals;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count = Integer.parseInt(scan.nextLine());
        intervals = new int[count][2];
        for (int i = 0; i < count; i++) {
            intervals[i] = Arrays.stream(scan.nextLine().split(","))
                    .mapToInt(Integer::parseInt).toArray();
        }

        mergeSort(intervals);

        System.out.println(checkIntervalsOverlapping(intervals));
    }

    private static boolean checkIntervalsOverlapping(int[][] intervals) {
        for (int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i][1] >= intervals[i + 1][0]) return true;
        }
        return false;
    }

    private static void mergeSort(int[][] arr) {
        if(arr.length <= 1) return;

        int[][] first = new int[arr.length / 2][2];
        int[][] second = new int[arr.length - first.length][2];

        for (int i = 0; i < arr.length; i++) {
            if(i < first.length){
                first[i] = arr[i];
            } else {
                second[i - first.length] = arr[i];
            }
        }

        mergeSort(first);
        mergeSort(second);

        int indexFirst = 0;
        int indexSecond = 0;
        while(indexFirst + indexSecond < arr.length){
            if(indexSecond >= second.length ||
                    (indexFirst < first.length &&
                            first[indexFirst][0] < second[indexSecond][0])){
                arr[indexFirst + indexSecond] = first[indexFirst];
                indexFirst++;
            } else {
                arr[indexFirst + indexSecond] = second[indexSecond];
                indexSecond++;
            }
        }


    }


}