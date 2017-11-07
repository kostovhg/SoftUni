package app_Avatar.Entities;

public abstract class Bender {
    private String name;
    private int power;

    protected Bender(String name, int power) {
        this.setName(name);
        this.setPower(power);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    protected int getPower() {
        return power;
    }

    private void setPower(int power) {
        this.power = power;
    }

    public abstract Double getTotalPower();
}
