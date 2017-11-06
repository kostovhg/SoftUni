package p03_WildFarm.Entities;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    String livingRegion;

    public Mammal(String[] args) {
        super(args);
        this.livingRegion = args[3];
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]",
                this.animalType,
                this.animalName,
                getDecimalFormat(this.animalWeight),
                this.livingRegion,
                this.foodEaten
        );
    }

    private String getDecimalFormat(double val) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(val);
    }
}
