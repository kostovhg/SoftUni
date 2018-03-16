package exercise.c_Ferrari;

public class Ferrari implements Car {

    private String model = "488-Spider";
    private String driver;

    public Ferrari(String driver) {
        this.driver = driver;
    }

    @Override
    public String brakes() {
        return "Brakes!";
    }

    @Override
    public String gasPedal() {
        return "Zadu6avam sA!";
    }

    public String getModel(){
        return this.model;
    }

    public String getDriver(){
        return this.driver;
    }

    @Override
    public String toString() {
        return String.format(
                "%s/%s/%s/%s",
                this.getModel(),
                this.brakes(),
                this.gasPedal(),
                this.getDriver());
    }


}
