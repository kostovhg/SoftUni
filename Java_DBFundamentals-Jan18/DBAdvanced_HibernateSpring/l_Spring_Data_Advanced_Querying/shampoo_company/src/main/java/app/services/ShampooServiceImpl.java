package app.services;

import app.model.enums.Size;
import app.model.labels.BasicLabel;
import app.model.shampoos.BasicShampoo;
import app.repositories.BasicShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ShampooServiceImpl implements ShampooService {

    private final BasicShampooRepository basicShampooRepository;

    @Autowired
    public ShampooServiceImpl(BasicShampooRepository basicShampooRepository) {
        this.basicShampooRepository = basicShampooRepository;
    }



    @Override
    public List<BasicShampoo> getAllBySize(Size size) {
        return this.basicShampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<BasicShampoo> getAllBySizeAndLabelIdOrderedByPriceAsc(Size size, Long id) {
        return this.basicShampooRepository.findAllBySizeAndLabel_IdOrderByPriceAscIdAsc(size, id);
    }

    @Override
    public List<BasicShampoo> getAllBySizeAndLabelOrderedByPriceAsc(Size size, BasicLabel label) {
        return this.basicShampooRepository.findAllBySizeAndLabelOrderByPriceAsc(size, label);
    }

    @Override
    public List<BasicShampoo> getAllBySizeOrLabel_IdOrderByPriceAsc(Size size, Long id){
        return this.basicShampooRepository.findAllBySizeOrLabel_IdOrderByPriceAscIdAsc(size, id);
    }

    @Override
    public List<BasicShampoo> getAll() {
        return this.basicShampooRepository.findAll();
    }

    @Override
    public List<BasicShampoo> findAllByPriceGreaterThanOrderByPriceDesIdAsc
            (BigDecimal price){
        return this.basicShampooRepository.findAllByPriceGreaterThanOrderByPriceDescIdAsc(price);
    }

    @Override
    public List<BasicShampoo> findAllByPriceLowerThan(BigDecimal price){
        return this.basicShampooRepository.findAllByPriceIsLessThan(price);
    }

    @Override
    public List<BasicShampoo> findAllByIngredientsIn(List<String> ingr){
        return this.basicShampooRepository.findAllByIngredients_NameIn(ingr);
    }

    @Override
    public List<BasicShampoo> findAllWithIngredientsCountLessThan(int size){
        return this.basicShampooRepository.findAllByIngredientsCountLessThan(size);
    }

    @Override
    public List<BasicShampoo> findAllByBrands(List<String> brands){
        return this.basicShampooRepository.findAllByBrandIn(brands);
    }

    @Override
    public Double findSumOfIngredients(String brands){
        return this.basicShampooRepository.calculateSumOfIngredients(brands);
    }
}
