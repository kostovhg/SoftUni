package lab.utils;

import org.modelmapper.*;

public class ConvertUtil {

    public ConvertUtil() {
    }

    public static <S, D> D convertCustom(S source, Class<D> destClass, ExpressionMap<S, D>... exprMaps){
        ModelMapper mapper = new ModelMapper();
        TypeMap<S, D> typeMap = mapper.createTypeMap((Class<S>) source.getClass(), destClass);

        for(ExpressionMap<S, D> exprMap : exprMaps){
            typeMap.addMappings(exprMap);
        }
        return typeMap.map(source);
    }
}
