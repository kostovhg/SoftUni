package app.services;

import app.model.ingredients.BasicIngredient;
import app.repositories.BasicIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class IngredientsServiceImpl implements IngredientsService {

    private final BasicIngredientsRepository basicIngredientsRepository;

    @Autowired
    public IngredientsServiceImpl(BasicIngredientsRepository basicIngredientsRepository) {
        this.basicIngredientsRepository = basicIngredientsRepository;
    }

    @Override
    public List<BasicIngredient> findAllByNameStartingWith(String start){
        return this.basicIngredientsRepository.findAllByNameIsStartingWithOrderByIdAsc(start);
    }

    @Override
    public List<BasicIngredient> findAllByNameAndSortByPrice(List<String> names){
        return this.basicIngredientsRepository.findAllByNameInOrderByPriceAscIdAsc(names);
    }

    @Override
    public BasicIngredient findByName(String name){
        return this.basicIngredientsRepository.findByName(name);
    }

    @Override
    public void deleteByName(String name){
        this.basicIngredientsRepository.deleteByName(name);
    }

    @Override
    public void increaseAllPricesWithPercent(Double percent){
        this.basicIngredientsRepository.increaseAllPricesWithPercent(percent);
    }

    @Override
    public void increaseAllPricesWithPercent(Double percent, List<String> names){
        this.basicIngredientsRepository.increaseAllPricesWithPercent(percent, names);
    }
}
