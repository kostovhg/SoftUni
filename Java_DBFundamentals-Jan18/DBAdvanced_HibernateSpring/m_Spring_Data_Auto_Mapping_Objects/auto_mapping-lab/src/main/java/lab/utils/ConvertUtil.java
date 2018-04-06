package lab.utils;

import lab.dto.EmployeeDTO;
import lab.models.entities.Employee;
import org.modelmapper.ExpressionMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.ui.ModelMap;

public class ConvertUtil {

    public ConvertUtil() {
    }

    // moved to MapperUtil
//    public <S, D> D convert (S source, Class<D> destinationClass){
//        ModelMapper mapper = new ModelMapper();
//        return mapper.map(source, destinationClass);
//    }

    public static <S, D> D convertCustom(S source, Class<D> destClass, ExpressionMap<S, D>... exprMaps){
        ModelMapper mapper = new ModelMapper();
        TypeMap<S, D> typeMap = mapper.createTypeMap((Class<S>) source.getClass(), destClass);

        for(ExpressionMap<S, D> exprMap : exprMaps){
            typeMap.addMappings(exprMap);
        }
        return typeMap.map(source);
    }


}
