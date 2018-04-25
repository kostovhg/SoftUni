package car_dealer.dto.bingings;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierDto implements Serializable {


    @Expose
    private String name;

    @Expose
    private boolean isImporter;

    public SupplierDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return this.isImporter;
    }

    public void setImporter(boolean importer) {
        this.isImporter = importer;
    }
}