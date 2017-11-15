package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.IRepair;

public class Repair implements IRepair {

    private String partName;
    private int hoursWorked;

    private void setPartName(String partName) {
        this.partName = partName;
    }

    private void setHoursWorked(String hoursWorked) {
        this.hoursWorked = Integer.parseInt(hoursWorked);
    }

    public Repair(String part, String hours){
        this.setPartName(part);
        this.setHoursWorked(hours);
    }

    @Override
    public String toString(){
        return String.format("Part Name: %s Hours Worked: %d",
                this.partName,
                this.hoursWorked);
    }
}
