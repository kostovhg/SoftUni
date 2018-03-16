package exercise.g_Mankind;

import static exercise.g_Mankind.Constants.*;

public class Worker extends Human {

    private double weekSalary;
    private double workHoursPerDay;

    public Worker(String[] args) {
        super(args[0], args[1]);
        this.setWeekSalary(args[2]);
        this.setWorkingHours(args[3]);
    }

    private void setWeekSalary(String weekSalary) {
        double decimalSalary = Double.parseDouble(weekSalary);
        if (decimalSalary < MIN_SALARY) {
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            VALUE_MISMATCH,
                            WEEK_SALARY
                    )
            );
        }
        this.weekSalary = decimalSalary;
    }

    private void setWorkingHours(String workingHours) {
        double hours = Double.parseDouble(workingHours);
        if (hours < MIN_DAILY_HOURS || hours > MAX_DAILY_HOURS) {
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            VALUE_MISMATCH,
                            WORK_HOURS_PER_DAY
                    )
            );
        }
        this.workHoursPerDay = hours;
    }

    @Override
    protected void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() <= LAST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            LENGTH,
                            MORE_THAN,
                            String.valueOf(LAST_NAME_MIN_LEN),
                            LAST_NAME
                    )
            );
        }
        super.setLastName(lastName);
    }

    private Double getSalaryPerHour() {
        return this.weekSalary / this.workHoursPerDay / 7.0;
    }

    public String toString() {
        return String.format(
                WORKER_TO_STRING_FORMAT,
                super.toString(),
                this.weekSalary,
                this.workHoursPerDay,
                this.getSalaryPerHour()
        );
    }
}
