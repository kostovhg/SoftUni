package exercise.f_BirthdayCelebrations;

public class Robot implements Identifiable {

    private String model;
    private String id;

    public Robot(String[] args) {
        this.setModel(args[1]);
        this.setId(args[2]);
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
