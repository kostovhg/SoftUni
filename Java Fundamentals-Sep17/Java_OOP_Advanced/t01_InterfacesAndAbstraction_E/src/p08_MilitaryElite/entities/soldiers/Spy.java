package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.interfaces.ISpy;

public class Spy extends Soldier implements ISpy {

    private String codeNumber;

    public Spy(String[] args) {
        super(args);
        this.setCodeNumber(args[5]);
    }

    private void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    private String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString(){
        return String.format("%s\nCode Number: %s",
                super.toString(),
                this.getCodeNumber());
    }
}
