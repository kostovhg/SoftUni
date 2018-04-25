package car_dealer.services.api;

import car_dealer.dto.bingings.PartDto;
import car_dealer.entities.Part;

public interface PartService {
    void save(Part part);

    void saveAll(PartDto[] parts);
}
