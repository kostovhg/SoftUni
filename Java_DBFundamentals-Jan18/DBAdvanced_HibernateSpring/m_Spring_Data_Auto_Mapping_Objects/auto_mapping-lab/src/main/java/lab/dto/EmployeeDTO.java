package lab.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class EmployeeDTO implements Serializable {


    private static final long serialVersionUID = 5750839802657508267L;
    private String firstName;
    private String lastName;
    private String initials;
    private BigDecimal salary;
    private String addressCityName;
    private String managerLastName;

    public EmployeeDTO() {
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getAddressCityName() {
        return this.addressCityName;
    }

    public void setAddressCityName(String addressCityName) {
        this.addressCityName = addressCityName;
    }

    public String getInitials() {
        return this.initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getManagerLastName() {
        return this.managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", initials='" + this.initials + '\'' +
                ", salary=" + this.salary +
                ", addressCityName='" + this.addressCityName + '\'' +
                '}';
    }

    public String getAsString() {
        return this.firstName + " " + this.lastName + " " + this.salary;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
