package lab.p03_employee_info.models;

import lab.p03_employee_info.contracts.Formatter;
import lab.p03_employee_info.contracts.InfoProvider;

public class ConsoleClient {

    private Formatter formatter;
    private InfoProvider infoProvider;

    public ConsoleClient(Formatter formatter, InfoProvider infoProvider) {
        this.formatter = formatter;
        this.infoProvider = infoProvider;
    }

    public void printEmployeesByName(){
        System.out.println(this.formatter.format(this.infoProvider.getEmployeesByName()));
    }

    public void printEmployeesBySalary() {
        System.out.println(this.formatter.format(this.infoProvider.getEmployeesBySalary()));
    }

}
