package car_dealer.dto.bingings;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CarDto implements Serializable  {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Long travelledDistance;

    public CarDto() {
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
