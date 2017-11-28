import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        /* First problem
        Class aClas = Reflection.class;
        System.out.println(aClas);
        System.out.println(aClas.getSuperclass());
        Class[] interfaces = aClas.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface);
        }
        Reflection ref = (Reflection) aClas.newInstance();
        System.out.println(ref);
        */

        /* Second problem
        Arrays.stream(Reflection.class.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("get"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.println(m.getName() + " will return " + m.getReturnType()));

        Arrays.stream(Reflection.class.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.println(m.getName() + " and will set field of " + m.getParameterTypes()[0]));*/

        Arrays.stream(Reflection.class.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.println(f.getName() + " must be private!"));

        Arrays.stream(Reflection.class.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("get"))
                .filter(m -> !Modifier.isPublic(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.println(m.getName() + " have to be public!"));

        Arrays.stream(Reflection.class.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(m -> System.out.println(m.getName() + " have to be private!"));


    }

    /* Unused method to check if class method is getter
    public static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

    // Unused method to check if class method is setter
    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }*/
}


