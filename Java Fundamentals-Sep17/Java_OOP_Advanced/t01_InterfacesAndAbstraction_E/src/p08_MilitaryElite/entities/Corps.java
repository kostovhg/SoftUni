package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.ICorps;

public enum Corps implements ICorps {
    AIRFORCE("Airforce"), MARINES("Marines");

    private final String type;

    Corps(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return this.type;
    }
}
