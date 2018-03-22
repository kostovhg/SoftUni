package minionsORM.utilities;

public enum Evilness {
    GOOD(1, "good"),
    BAD(2, "bad"),
    EVIL(3, "evil"),
    SUPER_EVIL(4, "super evil");

    private int factor;
    private String value;

    private Evilness(int factor, String value){
        this.factor = factor;
        this.value = value;
    }

    public int factorOf(){
        return this.factor;
    }

    public String valueOf(){
        return this.value;
    }

    public Evilness getByFactor(int factor){
        for (Evilness evilness : values()) {
            if(evilness.factor == factor){
                return evilness;
            }
        }
        return null;
    }

    public Evilness getByValue(String value){
        for (Evilness evilness : values()) {
            if(evilness.value.equals(value)){
                return evilness;
            }
        }
        return null;
    }
}
