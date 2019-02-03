package chushka.domain.entities;

public enum ProductType {

    FOOD("Food"),
    DOMESTIC("Domestic"),
    HEALTH("Health"),
    COSMETIC("Cosmetic"),
    OTHER("Other");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static ProductType fromString(String text) {
        for (ProductType b : ProductType.values()) {
            if (b.getType().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
