package p07_GenericAddAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 1, 2, null, 2, null);

        List<Double> doubleList = new ArrayList<>();
        Collections.addAll(doubleList, 1.2, 3.2, 5.5);

        List<Number> destination = new ArrayList<>();

        ListUtils.addAll(destination, integerList);
        ListUtils.addAll(destination, doubleList);

        System.out.println(destination);
    }
}
