package e_WorkForce.contracts;

/**
 * Interface for Employees that detaches concrete classes from
 * classes that are using them. Allowing to add many different employees
 */
public interface Employee {

    String getName();

    int getWorkingHoursPerWeek();
}
