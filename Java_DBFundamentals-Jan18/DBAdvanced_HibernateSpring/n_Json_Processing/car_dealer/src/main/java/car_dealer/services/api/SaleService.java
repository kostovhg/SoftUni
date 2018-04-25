package car_dealer.services.api;

import car_dealer.dto.models.SaleModel;
import car_dealer.entities.Sale;

import java.util.List;

public interface SaleService {
    void save(Sale sale);

    void insertRandomData();

    List<SaleModel> getAllSaleDetails();
}
