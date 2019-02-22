package casebook.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.Set;

public interface ModelParser {

    <S, D> D convert(S source, Class<D> destinationClass);

    <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap);

    <S, D> List<D> convert(Iterable<S> iterableSource, Class<D> destinationClass);

    ModelMapper getMapper();

    <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass);
}
