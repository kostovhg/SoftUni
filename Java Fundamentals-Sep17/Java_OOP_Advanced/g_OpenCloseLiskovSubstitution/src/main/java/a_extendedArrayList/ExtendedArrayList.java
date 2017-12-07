package a_extendedArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.BiFunction;

public class ExtendedArrayList<T extends Comparable<T>> extends ArrayList<T> {

    T max() {
        BiFunction<T, T, Boolean> function = (t1, t2) -> t1.compareTo(t2) > 0 ;
        return getMaxOrMin(function);
    }

    T min() {
        BiFunction<T, T, Boolean> function = (t1, t2) -> t1.compareTo(t2) < 0;
        return getMaxOrMin(function);
    }

    private T getMaxOrMin(BiFunction<T, T, Boolean> function){
        T minOrMax = null;
        Iterator<T> iterator = super.iterator();
        while (iterator.hasNext()){
            T nextElement = iterator.next();
            if (minOrMax == null || function.apply(nextElement, minOrMax)){
                minOrMax = nextElement;
            }
        }
        return minOrMax;
    }

    public static void main(String[] args) {
        ExtendedArrayList<Integer> nums = new ExtendedArrayList<Integer>();

        nums.add(100);
        nums.add(10);
        nums.add(1000);
        nums.add(-100);

        System.out.println(nums.max());
        System.out.println(nums.min());

    }

}
