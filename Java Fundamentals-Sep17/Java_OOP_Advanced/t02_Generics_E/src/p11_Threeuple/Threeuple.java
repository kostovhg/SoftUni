package p11_Threeuple;

public class Threeuple<T, E, P>{

    private T item1;
    private E item2;
    private P item3;

    public Threeuple(T item1, E item2, P item3) {
        this.setFirstItem(item1);
        this.setSecondItem(item2);
        this.setThirdItem(item3);
    }

    public T getFirstItem() {
        return item1;
    }

    public void setFirstItem(T item) {
        this.item1 = item;
    }

    public E getSecondItem() {
        return item2;
    }

    public void setSecondItem(E item) {
        this.item2 = item;
    }

    public P getThirdItem() {
        return item3;
    }

    public void setThirdItem(P item) {
        this.item3 = item;
    }


    @Override
    public String toString() {
        return String.format("%s -> %s -> %s",
                this.getFirstItem(),
                this.getSecondItem(),
                this.getThirdItem());
    }
}
