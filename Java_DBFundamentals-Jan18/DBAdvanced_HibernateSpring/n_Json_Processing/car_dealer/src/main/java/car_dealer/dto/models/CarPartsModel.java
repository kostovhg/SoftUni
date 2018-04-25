package car_dealer.dto.models;


import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarPartsModel implements Serializable  {

    @Expose
    private CarModel car;

    @Expose
    private List<PartModel> parts;

    public CarPartsModel() {
        this.parts = new ArrayList<>();
    }

    public CarModel getCar() {
        return this.car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    public List<PartModel> getParts() {
        return this.parts;
    }

    public void setParts(List<PartModel> parts) {
        this.parts = parts;
    }
}
