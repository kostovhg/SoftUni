package casebook.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelParserImpl implements ModelParser{

    private final ModelMapper mapper;

    public ModelParserImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.mapper.addMappings(propertyMap);
        return mapper.map(source, destinationClass);
    }

    @Override
    public <S, D> List<D> convert(Iterable<S> sourceIter, Class<D> destinationClass){
        List<D> resultList = new ArrayList<>();

        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultList.add(d);
        }

        return resultList;
    }

    @Override
    public <S, D> Set<D> convertToSet(Iterable<S> sourceIter, Class<D> destinationClass){
        Set<D> resultSet = new HashSet<>();

        for (S s : sourceIter) {
            D d = convert(s, destinationClass);
            resultSet.add(d);
        }

        return resultSet;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }
}
