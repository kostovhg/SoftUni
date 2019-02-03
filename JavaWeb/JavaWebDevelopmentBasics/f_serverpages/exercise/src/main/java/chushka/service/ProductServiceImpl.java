package chushka.service;

import chushka.domain.entities.Product;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.ProductRepository;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(productServiceModel.getType());

        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts(){
        return this.productRepository.findAll().stream()
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductByName(String name){
        return this.modelMapper
                .map(this.productRepository.findByName(name), ProductServiceModel.class);

    }
}
