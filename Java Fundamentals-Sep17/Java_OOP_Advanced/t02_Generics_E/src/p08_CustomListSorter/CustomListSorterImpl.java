package p08_CustomListSorter;

import java.util.Collections;

public class CustomListSorterImpl {

    public static <T extends Comparable> void sort(CustomList<T> listToBeSorted) {
        Collections.sort(listToBeSorted.get());
    }
}
