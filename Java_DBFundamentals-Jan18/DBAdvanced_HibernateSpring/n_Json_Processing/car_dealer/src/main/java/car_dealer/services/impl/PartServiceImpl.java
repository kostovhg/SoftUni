package car_dealer.services.impl;

import car_dealer.dto.bingings.PartDto;
import car_dealer.entities.Part;
import car_dealer.entities.Supplier;
import car_dealer.repositories.PartRepository;
import car_dealer.repositories.SupplierRepository;
import car_dealer.services.api.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private PartRepository partRepository;
    private SupplierRepository supplierRepository;
    private ModelMapper mapper;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Part part) {
        this.partRepository.save(part);
    }

    @Override
    public void saveAll(PartDto[] parts){
        Random random = new Random();
        List<Supplier> allSuppliers = this.supplierRepository.findAll();
        for (PartDto partDto : parts) {
            Part part = this.mapper.map(partDto, Part.class);
            int randomIndex = random.nextInt(allSuppliers.size());
            part.setSupplier(allSuppliers.get(randomIndex));
            this.save(part);
        }
    }
}
