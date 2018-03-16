package exercise.e_BorderControl;

public class BaseUnit implements Identifiable {

    private String id;

    public BaseUnit(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean validateID(String idEnd) {
        return this.id.endsWith(idEnd);
    }
}
