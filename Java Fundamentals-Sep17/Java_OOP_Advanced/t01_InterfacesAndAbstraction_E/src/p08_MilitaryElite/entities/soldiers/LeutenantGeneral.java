package p08_MilitaryElite.entities.soldiers;

import p08_MilitaryElite.interfaces.ILeutenantGeneral;
import p08_MilitaryElite.interfaces.IPrivate;
import p08_MilitaryElite.interfaces.ISoldier;

import java.util.*;

public class LeutenantGeneral extends Private implements ILeutenantGeneral {

    private Collection<IPrivate> privates;

    public LeutenantGeneral(
            int id, String firstName, String lastName,
            double salary,
             Collection<IPrivate> privates) {
        super(id, firstName, lastName, salary);
        this.setPrivates(privates);
    }

    private void setPrivates(Collection<IPrivate> privatesCollection) {
        if(privatesCollection != null) {
            this.privates = new ArrayList<>(privatesCollection);
            return;
        }
        this.privates = new ArrayList<>();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Privates:").append(System.lineSeparator());
        this.getPrivates().forEach(p ->
                sb.append("  ").append(p));
        return sb.toString();
    }

    @Override
    public Collection<IPrivate> getPrivates(){
        return this.privates;
    }
}
