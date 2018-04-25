package car_dealer.services.api;

import car_dealer.dto.bingings.SupplierDto;
import car_dealer.dto.models.SupplierModel;
import car_dealer.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void save(Supplier supplier);

    void saveAll(SupplierDto[] suppliers);

    void saveAll(List<SupplierDto> suppliers);

//    List<SupplierModel> getAllLocalSuppliers();
}
