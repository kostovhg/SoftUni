package e_WorkForce.models.employees;

public class PartTimeEmployee extends AbstractEmployee {

    private static final int PART_TIME_WORKING_HOURS_PER_WEEK = 20;

    public PartTimeEmployee(String name) {
        super(name, PART_TIME_WORKING_HOURS_PER_WEEK);
    }
}
