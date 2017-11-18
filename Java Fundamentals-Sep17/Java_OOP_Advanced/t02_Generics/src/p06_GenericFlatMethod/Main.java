package p06_GenericFlatMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 1, 2, null, 2, null);

        List<Double> doubles = new ArrayList<>();
        Collections.addAll(doubles, 1.2, 3.2);

        List<List<? extends Number>> jagged = new ArrayList<>();
        Collections.addAll(jagged, integerList, doubles);

        List<Number> dest = new ArrayList<>();

        ListUtils.flatten(dest, jagged);
        System.out.println(dest);
    }
}
