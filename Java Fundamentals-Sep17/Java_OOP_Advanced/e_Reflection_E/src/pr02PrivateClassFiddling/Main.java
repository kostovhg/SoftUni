package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.peshoslav.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input;
        Constructor<BlackBoxInt> constructor = BlackBoxInt.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt newInstance = constructor.newInstance();
        Field field = BlackBoxInt.class.getDeclaredField("innerValue");
        field.setAccessible(true);

        while (true) {
			input = reader.readLine().split("_");

			if("END".equalsIgnoreCase(input[0])){
			    break;
            }

            String command = input[0];
			int value = Integer.parseInt(input[1]);

			Method declaredMethod;
            try {
                declaredMethod = BlackBoxInt.class.getDeclaredMethod(command, int.class);
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException nsme){
                // todo: say that there is no such method in the class
                continue;
            }
            declaredMethod.invoke(newInstance, value);

            System.out.println(field.get(newInstance));
        }
    }
}
