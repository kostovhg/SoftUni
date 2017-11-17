package p08_MilitaryElite.entities;

import p08_MilitaryElite.interfaces.IRepair;

public class Repair implements IRepair {

    private String partName;
    private int hoursWorked;

    public Repair(String part, int hours){
        this.setPartName(part);
        this.setHoursWorked(hours);
    }

    private void setPartName(String partName) {
        this.partName = partName;
    }

    private void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString(){
        return String.format("Part Name: %s Hours Worked: %d",
                this.getPartName(),
                this.getHoursWorked());
    }

}
