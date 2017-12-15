package e_WorkForce.models.employees;

import e_WorkForce.contracts.Employee;

/**
 * Abstract class keeps the common methods and fields for all subclasses
 */
abstract class AbstractEmployee implements Employee {

    String name;
    int workingHoursPerWeek;

    public AbstractEmployee(String name, int hours) {
        this.name = name;
        this.workingHoursPerWeek = hours;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getWorkingHoursPerWeek() {
        return this.workingHoursPerWeek;
    }
}
