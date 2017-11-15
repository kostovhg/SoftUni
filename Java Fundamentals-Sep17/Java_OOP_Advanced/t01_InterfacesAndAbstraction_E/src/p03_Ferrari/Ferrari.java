package p03_Ferrari;

public class Ferrari implements Car {

    private final String model = "488-Spider";
    private String driver;

    public Ferrari(String driver) {
        this.setDriver(driver);
    }

    public String getDriver() {
        return driver;
    }

    private void setDriver(String driver) {
        this.driver = driver;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String getBrakes() {
        return "Brakes!";
    }

    @Override
    public String getGazPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString(){
        return String.format("%s/%s/%s/%s",
                getModel(),
                getBrakes(),
                getGazPedal(),
                getDriver());
    }
}
