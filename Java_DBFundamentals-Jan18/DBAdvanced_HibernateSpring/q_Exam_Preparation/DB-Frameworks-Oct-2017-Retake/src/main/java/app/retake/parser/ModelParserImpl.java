package app.retake.parser;

import app.retake.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    private ModelMapper mapper;

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
}
