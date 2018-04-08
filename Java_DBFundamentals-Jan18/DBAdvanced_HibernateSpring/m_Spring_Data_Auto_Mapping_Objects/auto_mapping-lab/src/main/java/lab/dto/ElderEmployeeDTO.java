package lab.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ElderEmployeeDTO implements Serializable {


    private static final long serialVersionUID = -3196128513580121115L;
    private String fullName;
    private BigDecimal salary;
    private String managerLastName;

    public ElderEmployeeDTO() {
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getManagerLastName() {
        return this.managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f - Manager: %s",
                this.fullName,
                this.salary,
                this.managerLastName);
    }
}
