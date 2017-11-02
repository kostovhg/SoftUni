package p03_Mankind;

public class Worker extends Human {

    private double weekSalary;
    private int workHoursDay;

    public Worker(String fName, String lName, double wSalary, int wDaily) {
        super(fName, lName);
        this.setLastName(lName);
        this.setWeekSalary(wSalary);
        this.setWorkHoursDay(wDaily);
    }

    protected double getSalaryPerHour() {
        return weekSalary / (workHoursDay * 7);
    }

    private double getWeekSalary() {
        return weekSalary;
    }

    private void setWeekSalary(double wSalary) throws IllegalArgumentException {
        if (wSalary < 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = wSalary;
    }

    private int getWorkHoursDay() {
        return workHoursDay;
    }


    private void setWorkHoursDay(int wDailyHours) throws IllegalArgumentException {
        if (wDailyHours < 1 || wDailyHours > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workHoursDay = wDailyHours;
    }

    @Override
    protected void setLastName(String lName) throws IllegalArgumentException {
        if (lName.length() < 4) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString())
                .append("Week Salary: ").append(this.getWeekSalary())
                .append(System.lineSeparator())
                .append("Hours per day: ").append(this.getWorkHoursDay())
                .append(System.lineSeparator())
                .append("Salary per hour: ")
                .append(String.format("%.2f", this.getSalaryPerHour()))
                .append(System.lineSeparator());

        return sb.toString();
    }
}
