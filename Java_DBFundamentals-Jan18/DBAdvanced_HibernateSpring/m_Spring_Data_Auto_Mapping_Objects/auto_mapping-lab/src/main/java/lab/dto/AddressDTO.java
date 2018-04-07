package lab.dto;

import java.io.Serializable;


public class AddressDTO implements Serializable {


    private static final long serialVersionUID = -6465962864575669269L;
    private String cityName;
    private String street;

    public AddressDTO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressDTO: " +
                "cityName:" + this.cityName +
                ", street: " + this.street;
    }
}
