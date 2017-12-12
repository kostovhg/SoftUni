package lab.p03_employee_info;

import lab.p03_employee_info.contracts.Formatter;
import lab.p03_employee_info.contracts.InfoProvider;
import lab.p03_employee_info.models.ConsoleClient;
import lab.p03_employee_info.models.ConsoleFormatter;
import lab.p03_employee_info.models.EmployeeInfoProvider;

public class Main {

    public static void main(String[] args) {

        Formatter formatter = new ConsoleFormatter();
        InfoProvider infoProvider = new EmployeeInfoProvider();
        ConsoleClient consoleClient = new ConsoleClient(formatter, infoProvider);

        consoleClient.printEmployeesByName();
        consoleClient.printEmployeesBySalary();
    }
}
