package h_1984.models;

import h_1984.contracts.ConspiracyObjects;

public abstract class ConspiracyObject implements ConspiracyObjects {

    private String id;

    protected ConspiracyObject(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}
