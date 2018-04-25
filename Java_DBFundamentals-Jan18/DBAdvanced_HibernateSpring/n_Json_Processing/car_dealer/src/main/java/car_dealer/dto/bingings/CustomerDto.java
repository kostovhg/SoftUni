package car_dealer.dto.bingings;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

public class CustomerDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private Date birthDate;

    @Expose
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        this.isYoungDriver = youngDriver;
    }
}