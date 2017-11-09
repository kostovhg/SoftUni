package need_for_speed.entities.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* @startuml
* class PerformanceCar {
*   -addOns : List<String>;
*   #setHorsePower(int) <<override>>;
*   #setSuspension(int) <<override>>;
*   -getAddons()
* }
* PerformanceCar --|> Car
* @enduml
 */

public class PerformanceCar extends Car{

    private final List<String> addOns;

    public PerformanceCar(String brand, String model,
                          int yearOfProduction, int horsePower,
                          int acceleration, int suspension, int durability) {
        super(brand, model, yearOfProduction, horsePower, acceleration, suspension, durability);
        this.addOns = new ArrayList<>();
    }

    private List<String> getAddOns() {
        return Collections.unmodifiableList(this.addOns);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        super.setHorsePower(horsePower + (horsePower * 50) / 100);
    }

    @Override
    protected void setSuspension(int suspension) {
        super.setSuspension(suspension - (suspension * 25) / 100);
    }

    @Override
    public void tune(int tuneIndex, String addOn) {
        super.tune(tuneIndex, addOn);
        this.addOns.add(addOn);
    }

    @Override
    public String toString() {
        return String.format("%sAdd-ons: %s",
                super.toString(),
                this.getAddOns().size() == 0 ?
                        "None" :
                        String.join(", ", this.getAddOns()));
    }
}
