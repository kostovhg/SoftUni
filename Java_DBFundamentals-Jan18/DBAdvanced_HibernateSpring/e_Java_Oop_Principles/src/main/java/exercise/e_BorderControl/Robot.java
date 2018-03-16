package exercise.e_BorderControl;

public class Robot extends BaseUnit {

    private String model;

    public Robot(String[] args) {
        super(args[1]);
        this.setModel(args[0]);
    }

    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        this.model = model;
    }
}
