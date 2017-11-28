package l_InfernoInfinityCompareTo.enumerations;

public enum GemType {
    RUBY(7, 2, 5),
    EMERALD(1, 4, 9),
    AMETHYST(2, 8, 4);

    private int strength;

    private int agility;
    private int vitality;
    GemType(int s, int a, int v){
        this.strength = s;
        this.agility = a;
        this.vitality = v;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getVitality() {
        return this.vitality;
    }
}
