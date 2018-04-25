package xmlProcessingE.domain.dto;

import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import xmlProcessingE.domain.dto.view.products.ProductsInRangeModel;
import xmlProcessingE.domain.model.Product;
import xmlProcessingE.domain.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 25.7.2017 г..
 */
public final class DTOConvertUtil {

    private DTOConvertUtil() {
    }

    public static <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, destinationClass);
    }

    public static <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        List<D> resultList = new ArrayList<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    public static <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        Set<D> resultSet = new HashSet<>();
        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }

    public static List<ProductsInRangeModel> convert(List<Product> product) {
        List<ProductsInRangeModel> result = new ArrayList<>();

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<Product, ProductsInRangeModel>() {
            @Override
            protected void configure() {
                using(new Converter<String, String>() {
                    @Override
                    public String convert(MappingContext<String, String> context) {
                        Product src = (Product) context.getParent().getSource();
                        User seller = src.getSeller();
                        if(seller.getFirstName() == null){
                            return seller.getLastName();
                        } else {
                            return seller.getFirstName() + " " + seller.getLastName();
                        }
                    }
                }).map(source.getSeller().getLastName()).setSellerName("fullName");
            }
        });
        for (Product p : product) {
            result.add(mapper.map(p, ProductsInRangeModel.class));
        }

        return result;
    }
}
