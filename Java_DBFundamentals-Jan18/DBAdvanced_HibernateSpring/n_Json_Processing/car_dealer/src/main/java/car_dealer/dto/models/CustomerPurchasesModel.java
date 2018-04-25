package car_dealer.dto.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerPurchasesModel implements Serializable {

    @Expose
    private String name;

    @Expose
    private int boughtCars;

    @Expose
    private BigDecimal spendMoney;

    public CustomerPurchasesModel() {
    }

    public CustomerPurchasesModel(String name, int boughtCars, BigDecimal spendMoney) {
        this.name = name;
        this.boughtCars = boughtCars;
        this.spendMoney = spendMoney;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoughtCars() {
        return this.boughtCars;
    }

    public void setBoughtCars(int boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpendMoney() {
        return this.spendMoney;
    }

    public void setSpendMoney(BigDecimal spendMoney) {
        this.spendMoney = spendMoney;
    }
}
