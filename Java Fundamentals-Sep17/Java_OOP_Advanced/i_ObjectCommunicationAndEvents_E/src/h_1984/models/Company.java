package h_1984.models;

import h_1984.annotations.ObservableField;

public class Company extends BaseSubject {

    @ObservableField
    private int turnover;
    @ObservableField
    private int revenue;


    public Company(String id, String name, int turnover, int revenue) {
        super(id, name);
        this.turnover = turnover;
        this.revenue = revenue;
    }
}
