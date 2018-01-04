package pawInc.contracts;

public interface IAnimal {
    String getName();

    int getAge();

    boolean isCleansed();

    boolean isCastrated();

    void cleanse();

    void castrate();
}
