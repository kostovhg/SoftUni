package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.entities.Soldier;
import p08_MilitaryElite.interfaces.ISpy;

public class Spy extends Soldier implements ISpy {

    private String codeNumber;

    public Spy(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.setCodeNumber(codeNumber);
    }

    private void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
                .append(System.lineSeparator());
        sb.append("Code Number: ")
                .append(this.getCodeNumber())
                .append(System.lineSeparator());
        return sb.toString();
    }
}
