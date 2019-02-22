package org.softuni.sboj.util;

public enum Sector {
    MEDICINE("Medicine"),
    CAR("Car"),
    FOOD("Food"),
    DOMESTIC("Domestic"),
    SECURITY("Security");

    private final String sector;

    Sector(String sector) {
        this.sector = sector;
    }

    public String getSector() {
        return this.sector;
    }
}

