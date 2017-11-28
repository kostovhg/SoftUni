package j_InfernoInfinity.enumerations;

public enum WeaponType {
    AXE(5, 10, 4),
    SWORD(4, 6, 3),
    KNIFE(3, 4, 2);

    private int minDamage;
    private int maxDamage;
    private int sockets;

    WeaponType(int min, int max, int sockets){
        this.minDamage = min;
        this.maxDamage = max;
        this.sockets = sockets;
    }

    public int getMinDamage() {
        return this.minDamage;
    }

    public int getMaxDamage() {
        return this.maxDamage;
    }

    public int getSockets() {
        return this.sockets;
    }
}
