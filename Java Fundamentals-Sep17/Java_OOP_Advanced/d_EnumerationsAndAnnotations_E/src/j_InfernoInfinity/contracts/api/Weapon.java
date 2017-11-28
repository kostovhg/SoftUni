package j_InfernoInfinity.contracts.api;

import j_InfernoInfinity.enumerations.GemType;

public interface Weapon {

    void addGem(GemType gem, int index);

    void removeGem(int index);

    String toString();
}
