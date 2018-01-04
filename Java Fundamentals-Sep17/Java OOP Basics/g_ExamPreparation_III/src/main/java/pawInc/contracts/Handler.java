package pawInc.contracts;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Handler {
    @SuppressWarnings("unchecked")
    String executeCommand(String command, List<String> params) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchMethodException;

    Manager getManager();
}
