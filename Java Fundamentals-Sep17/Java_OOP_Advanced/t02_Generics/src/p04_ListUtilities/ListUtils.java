package p04_ListUtilities;

import java.util.List;

public class ListUtils<T>{

    public static <T extends Comparable<T>> T getMin(List<T> list){
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        T min = list.get(0);
        for (T element :
                list) {
            if (min.compareTo(element) > 0){
                min = element;
            }
        }

        return min;
    }

    public static <T extends Comparable<T>> T getMax(List<T> list){
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        T max = list.get(0);
        for (T element :
                list) {
            if (max.compareTo(element) < 0){
                max = element;
            }
        }

        return max;

    }
}
