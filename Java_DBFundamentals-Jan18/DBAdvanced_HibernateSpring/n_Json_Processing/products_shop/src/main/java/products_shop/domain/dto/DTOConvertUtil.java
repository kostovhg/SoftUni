package products_shop.domain.dto;

import org.modelmapper.*;

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
}
