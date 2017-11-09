package need_for_speed.entities.cars;

/*
* @startuml
* abstract class Car {
*   -brand: String
*   -model: String
*   -yearOfProduction: int
*   -horsePower: int
*   -acceleration: int
*   -suspension: int
*   -durability: int
*   +toString() : String <<override>>
*   {method} +tune
* }
* class PerformanceCar
* class ShowCar
* ShowCar --|> Car
* PerformanceCar --|> Car
* @enduml
* */

public abstract class Car {
    private String brand;
    private String model;
    private int yearOfProduction;
    private int horsePower;
    private int acceleration;
    private int suspension;
    private int durability;

    Car(String brand, String model, int yearOfProduction, int horsePower,
        int acceleration, int suspension, int durability) {
        this.setBrand(brand);
        this.setModel(model);
        this.setYearOfProduction(yearOfProduction);
        this.setHorsePower(horsePower);
        this.setAcceleration(acceleration);
        this.setSuspension(suspension);
        this.setDurability(durability);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %d", this.getBrand(), this.getModel(), this.getYearOfProduction()));
        sb.append(System.lineSeparator());
        sb.append(String.format("%d HP, 100 m/h in %d s", this.getHorsePower(), this.getAcceleration()));
        sb.append(System.lineSeparator());
        sb.append(String.format("%d Suspension force, %d Durability", this.getSuspension(), this.getDurability()));
        return sb.toString();
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private int getYearOfProduction() {
        return yearOfProduction;
    }

    private void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getHorsePower() {
        return horsePower;
    }

    void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    private void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getDurability() {
        return durability;
    }

    private void setDurability(int durability) {
        this.durability = durability;
    }

    public int getCasualRacePoins() {
        return (this.horsePower / this.acceleration) + (this.suspension * this.durability);
    }

    public int getDragRacePoints(){
        return (this.horsePower / this.acceleration);
    }

    public int getDriftRacePoints(){
        return (this.suspension + this.durability);
    }

    public void tune(int tuneIndex, String addOn) {
        int horsePower = getHorsePower();
        int suspension = getSuspension();
        this.horsePower = (horsePower + tuneIndex);
        this.suspension = (suspension + tuneIndex / 2);
    }

    public void decreaseDurability(int laps, int length){
        this.durability -= laps * Math.pow(length, 2);
    }
}
