package p10_Tuple;

public class Tuple <T, E>{

    private T item1;
    private E item2;

    public Tuple(T item1, E item2) {
        this.setFirstItem(item1);
        this.setSecondItem(item2);
    }

    public T getFirstItem() {
        return item1;
    }

    public void setFirstItem(T item1) {
        this.item1=item1;
    }

    public E getSecondItem() {
        return item2;
    }

    public void setSecondItem(E item2) {
        this.item2=item2;
    }


    @Override
    public String toString() {
        return String.format("%s -> %s",
                this.getFirstItem(),
                this.getSecondItem());
    }
}
