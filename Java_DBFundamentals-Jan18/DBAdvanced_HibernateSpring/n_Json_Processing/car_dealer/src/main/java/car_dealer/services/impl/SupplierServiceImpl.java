package car_dealer.services.impl;

import car_dealer.dto.bingings.SupplierDto;
import car_dealer.dto.models.SupplierModel;
import car_dealer.entities.Supplier;
import car_dealer.repositories.SupplierRepository;
import car_dealer.services.api.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void save(Supplier supplier){
        this.supplierRepository.save(supplier);
    }

    @Override
    public void saveAll(SupplierDto[] suppliers) {
        for (SupplierDto supplierDto : suppliers) {
            Supplier supplier = this.mapper.map(supplierDto, Supplier.class);
            this.save(supplier);
        }
    }

    @Override
    public void saveAll(List<SupplierDto> suppliers) {
        for (SupplierDto supplierDto : suppliers) {
            Supplier supplier = this.mapper.map(supplierDto, Supplier.class);
            this.save(supplier);
        }
    }

//    @Override
//    public List<SupplierModel> getAllLocalSuppliers() {
//        List<Supplier> suppliers = this.supplierRepository.getAllLocalSuppliers();
//        List<SupplierModel> supplierModels = new ArrayList<>();
//        for (Supplier supplier : suppliers) {
//            SupplierModel supplierModel = this.mapper.map(supplier, SupplierModel.class);
//            supplierModel.setPartsCount(supplier.getParts().size());
//            supplierModels.add(supplierModel);
//        }
//        return supplierModels;
//    }
}
