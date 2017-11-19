package p03_GenericSwapMethodStings;

import java.util.List;

public class Box<T> {

    private T value;

    public Box(T val){
        this.setValue(val);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static <T> void swap(List<T> collection, int firstIndex, int secondIndex){
        T temp = collection.get(firstIndex);
        collection.set(firstIndex, collection.get(secondIndex));
        collection.set(secondIndex, temp);
    }

    @Override
    public String toString(){
        return String.format("%s: %s",
                value.getClass().toString().replace("class ", ""),
                this.getValue());
    }
}
