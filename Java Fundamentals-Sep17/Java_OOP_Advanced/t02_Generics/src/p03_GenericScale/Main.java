package p03_GenericScale;

public class Main {
    public static void main(String[] args) {

        Scale<String> stringScale = new Scale<>("a", "c");
        System.out.println(stringScale.getHeavier());

        Scale<Integer> integerScale = new Scale<>(1, 2);
        System.out.println(integerScale.getHeavier());

        Scale<Double> doubleScale = new Scale<>(5.0, 5.0);
        System.out.println(doubleScale.getHeavier());
    }
}
