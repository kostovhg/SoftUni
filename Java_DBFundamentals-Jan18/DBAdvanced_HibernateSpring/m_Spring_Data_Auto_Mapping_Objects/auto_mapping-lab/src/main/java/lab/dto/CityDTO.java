package lab.dto;

import java.io.Serializable;


public class CityDTO implements Serializable {


    private static final long serialVersionUID = 1996143312120158000L;
    private String name;

    public CityDTO() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
