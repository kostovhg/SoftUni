package pawInc.contracts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Engine {

    void run() throws IOException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchMethodException, IllegalAccessException;
}
