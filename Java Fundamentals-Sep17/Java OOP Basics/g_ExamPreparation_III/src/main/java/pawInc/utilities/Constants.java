package pawInc.utilities;

import java.util.HashMap;
import java.util.Map;

public final class Constants {
    public static final String SPLITTER_PATTERN = " \\| ";
    public static final int COMMAND_INDEX = 0;
    public static final String TERMINATING_COMMAND = "Paw Paw Pawah";
    public static final String COMMAND_CLASS_PATH = "pawInc.contracts.";
    public static final String MANAGER_INTERFACE_NAME = "Manager";
    public static final String COMMAND_CLASS_NAME_SUFFIX = "";

    // a hashMap for relation between primitive and Wrapper classes
    public static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_MAPPER = new HashMap<Class<?>, Class<?>>() {{
        put(int.class, Integer.class);
        put(double.class, Double.class);
        put(float.class, Float.class);
        put(long.class, Long.class);
        put(short.class, Short.class);
        put(byte.class, Byte.class);
        put(boolean.class, Boolean.class);
    }};
}
