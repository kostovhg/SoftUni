package p03_CarShopExtend.interfaces;

public interface Car {
    int TIRES = 4;

    String getModel();

    String getColor();

    int getHorsePower();

    @Override
    String toString();
}
