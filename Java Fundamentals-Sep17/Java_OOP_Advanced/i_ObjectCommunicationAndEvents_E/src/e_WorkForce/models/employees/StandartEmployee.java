package e_WorkForce.models.employees;

public class StandartEmployee extends AbstractEmployee {

    private static final int STANDARD_WORKING_HOURS_PER_WEEK = 40;

    public StandartEmployee(String name) {
        super(name, STANDARD_WORKING_HOURS_PER_WEEK);
    }
}
