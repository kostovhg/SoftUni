package p01_GenericBox;

public class Box<T> {

    private T value;

    public Box(T val){
        this.setValue(val);
    }

    @Override
    public String toString(){
        return String.format("%s: %s",
                value.getClass().toString().replace("class ", ""),
                this.getValue());
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
