package org.softuni.mostwanted.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    private final ModelMapper mapper;

    public ModelParserImpl() {
        this.mapper = new ModelMapper();
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.mapper.map(source, destinationClass);
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        this.mapper.addMappings(propertyMap);
        return this.convert(source, destinationClass);
    }

    @Override
    public ModelMapper getMapper(){
        return this.mapper;
    }
}
