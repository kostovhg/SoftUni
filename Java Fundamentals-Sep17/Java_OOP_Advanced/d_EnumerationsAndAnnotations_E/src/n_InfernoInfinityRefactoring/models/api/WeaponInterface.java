package n_InfernoInfinityRefactoring.models.api;

public interface WeaponInterface<T> extends Comparable<T> {

    String getName();

    void addGem(String gemType, int index);

    void removeGem(int index);

    double getItemLevel();
}
