package p09_CustomListIterator;

import java.util.Collections;

public class CustomListSorterImpl {

    public static <T extends Comparable> void sortWithCollection(CustomList<T> listToBeSorted) {
        Collections.sort(listToBeSorted.get());
    }

    public static <T extends Comparable> void sort(CustomList<T> list) {

        int n=list.get().size();

        for (int i=0; i < n; i++) {
            for (int j=0; j < (n - 1); j++) {
                if (list.get().get(j - 1).compareTo(list.get().get(j)) < 0) {
                    list.swap(j - 1, j);
                }
            }
        }
    }
}
