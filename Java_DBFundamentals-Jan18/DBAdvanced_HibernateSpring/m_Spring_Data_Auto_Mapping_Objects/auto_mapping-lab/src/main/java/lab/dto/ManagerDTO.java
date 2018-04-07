package lab.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ManagerDTO implements Serializable {


    private static final long serialVersionUID = 4536931635362361770L;
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> managedEmployees;
    private int managedEmployeesCount;

    public ManagerDTO() {
        this.managedEmployees = new HashSet<>();
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

    public Set<EmployeeDTO> getEmployees() {
        return this.managedEmployees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.managedEmployees = employees;
    }

    public int getManagedEmployeesCount() {
        return this.managedEmployees.size();
    }

    public void setManagedEmployeesCount(int managedEmployeesCount) {
        this.managedEmployeesCount = managedEmployeesCount;
    }

    @Override
    public String toString() {
        return String.format("%s %s | Employees: %d\n"+
        "\t- %s",
                this.firstName,
                this.lastName,
                this.managedEmployees.size(),
                String.join("\n\t- ",
                        this.managedEmployees.stream()
                                .map(EmployeeDTO::getAsString)
                                .collect(Collectors.toList())));
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
