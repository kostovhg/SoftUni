package p05_NullFinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Integer> integerList = new ArrayList<>();
        Collections.addAll(integerList, 1, 2, null, 2, null);

        List<Integer> maxInteger = ListUtils.getNullIndices(integerList);

        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "a", null, "c");

        List<Integer> minString = ListUtils.getNullIndices(strings);

        System.out.println(maxInteger);
        System.out.println(minString);
    }
}
