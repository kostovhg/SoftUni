package lab.dto;

import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class ManagerDTO implements Serializable {


    private static final long serialVersionUID = 6029753147039956211L;

    private String firstName;
    private String lastName;
    private List<EmployeeDTO> managedEmployees;
    private int managedEmployeesCount;

    public ManagerDTO() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDTO> getEmployees() {
        return this.managedEmployees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.managedEmployees = employees;
    }

    public int getManagedEmployeesCount() {
        return this.managedEmployees.size();
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d\n"+
        "\t- %s", this.firstName, this.lastName, this.managedEmployees.size(),
                String.join("\n\t- ", this.managedEmployees.stream().map(EmployeeDTO::getAsString).collect(Collectors.toList())));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
