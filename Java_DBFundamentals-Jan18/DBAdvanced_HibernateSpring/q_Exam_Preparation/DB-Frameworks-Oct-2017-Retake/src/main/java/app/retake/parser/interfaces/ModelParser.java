package app.retake.parser.interfaces;

import org.modelmapper.PropertyMap;

public interface ModelParser {

    <S,D> D convert(S source, Class<D> destinationClass);

    <S,D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap);
}
