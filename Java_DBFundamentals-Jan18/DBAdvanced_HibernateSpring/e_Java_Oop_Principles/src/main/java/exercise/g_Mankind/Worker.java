package exercise.g_Mankind;

import static exercise.g_Mankind.Constants.*;

public class Worker extends Human {

    public static final String INVALID_WEEK_SALARY = "Expected value mismatch!Argument: weekSalary";
    public static final String INVALID_WORK_HOURS_PER_DAY = "Expected value mismatch!Argument: workHoursPerDay";
    public static final String INVALID_LAST_NAME_LENGTH = "Expected length more than 3 symbols!Argument: lastName";
    private double weekSalary;
    private double workHoursPerDay;

    public Worker(String[] args) {
        super(args[0], args[1]);
        this.setWeekSalary(args[2]);
        this.setWorkingHours(args[3]);
    }

    private void setWeekSalary(String weekSalary) {
        double decimalSalary = Double.parseDouble(weekSalary);
        if (decimalSalary < 10) {
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, VALUE_MISMATCH, "weekSalary")
            );
        }
        this.weekSalary = decimalSalary;
    }

    private void setWorkingHours(String workingHours) {
        double hours = Double.parseDouble(workingHours);
        if (hours < 1.0 || hours > 12.0) {
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, VALUE_MISMATCH, "workHoursPerDay")
            );
        }
        this.workHoursPerDay = hours;
    }

    private Double getSalaryPerHour() {
        return this.weekSalary / this.workHoursPerDay / 7.0;
    }

    @Override
    protected void setLastName(String lastName) {
        if(lastName == null || lastName.trim().length() <= LAST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, LENGTH, MORE_THAN, String.valueOf(LAST_NAME_MIN_LEN), LAST_NAME)
            );
        }
        super.setLastName(lastName);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append(String.format("Week Salary: %.2f",this.weekSalary))
                .append(System.lineSeparator())
                .append(String.format("Hours per day: %.2f", this.workHoursPerDay))
                .append(System.lineSeparator())
                .append(String.format("Salary per hour: %.2f", this.getSalaryPerHour()))
                .append(System.lineSeparator());

        return sb.toString();
    }
}
