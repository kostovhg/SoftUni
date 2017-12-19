package h_1984.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class PrimitiveClassesToWrapperClassesMapper {

    private static final Map<Class<?>, Class<?>> primitiveToWrapperMapper = new LinkedHashMap<Class<?>, Class<?>>() {{
        put(int.class, Integer.class);
        put(double.class, Double.class);
        put(float.class, Float.class);
        put(long.class, Long.class);
        put(short.class, Short.class);
        put(byte.class, Byte.class);
        put(boolean.class, Boolean.class);
    }};

    public static Class<?> mapToWrapper(Class<?> primitiveType) {
        return primitiveToWrapperMapper.get(primitiveType);
    }
}
