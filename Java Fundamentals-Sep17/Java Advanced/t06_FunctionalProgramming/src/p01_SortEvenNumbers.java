import java.util.*;
import java.util.stream.Collectors;

public class p01_SortEvenNumbers {

    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        List<Integer> numbers = Arrays
                .stream(scann.nextLine().split("\\D+")).map(Integer::parseInt).collect(Collectors.toList());
        numbers.removeIf(n -> n % 2 != 0);
        printList(numbers);
        numbers.sort(Comparator.naturalOrder());
        printList(numbers);
    }

    private static void printList(List<Integer> list){
        System.out.println(list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
    }
}