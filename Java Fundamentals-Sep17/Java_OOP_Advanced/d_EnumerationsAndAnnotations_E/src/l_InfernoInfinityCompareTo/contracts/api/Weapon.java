package l_InfernoInfinityCompareTo.contracts.api;

import l_InfernoInfinityCompareTo.enumerations.GemType;

public interface Weapon extends Comparable<Weapon> {

    void addGem(GemType gem, int index);

    void removeGem(int index);

    double getItemLevel();

    static Weapon max(Weapon first, Weapon second){
        if(first.compareTo(second) >= 0){
            return first;
        } else {
            return second;
        }
    }
}
