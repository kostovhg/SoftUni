package org.softuni.ruk.model.dto.importJson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class ClientJSONImportDTO implements Serializable {

    @Expose
    @NotEmpty
    @SerializedName("first_name")
    private String firstName;

    @Expose
    @NotEmpty
    @SerializedName("last_name")
    private String lastName;

    @Expose
    @SerializedName("age")
    private int age;

    @Expose
    @NotEmpty
    @SerializedName("appointed_employee")
    @Pattern(regexp = "\\w+ \\w+")
    private String appointedEmployee;

    public ClientJSONImportDTO() {
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

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAppointedEmployee() {
        return this.appointedEmployee;
    }

    public void setAppointedEmployee(String appointedEmployee) {
        this.appointedEmployee = appointedEmployee;
    }
}
