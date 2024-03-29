package lab.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ManagerDTO implements Serializable {


    private static final long serialVersionUID = 4480617517372225632L;
    private String firstName;
    private String lastName;
    private Set<EmployeeDTO> managedEmployees;

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
