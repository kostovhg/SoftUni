package pawInc.commandHandler;

import pawInc.contracts.Handler;
import pawInc.contracts.Manager;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pawInc.utilities.Constants.*;

public class PawIncCommandHandler implements Handler {

    private Manager manager;
    private Map<String, Method> methods = new HashMap<>();

    public PawIncCommandHandler(Manager manager) throws ClassNotFoundException {
        this.manager = manager;
        this.setClassMethod(MANAGER_INTERFACE_NAME);
        // getting only the interface methods, because we are going to use only them
        // the normal class AnimalCenterManager has additional methods that are inherited
        // from object, and we are not going to use them
    }

    @Override
    public Manager getManager(){
        return this.manager;

    }

    @Override
    @SuppressWarnings("unchecked")
    public String executeCommand(String command, List<String> params) throws
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException,
            NoSuchMethodException {

        // get the method from input command
        Method method = this.methods.get(command);
        // get its parameters types
        Class[] methodParameterTypes = method.getParameterTypes();
        // empty array that will collect parameters after converting them from string to the type from method signature
        Object[] arguments = new Object[methodParameterTypes.length];
        // go trough all method types and convert each params member to concrete wrapper class
        for (int i = 0; i < methodParameterTypes.length; i++) {
            Class<?> parameterType = methodParameterTypes[i].isPrimitive() ?
                    PRIMITIVE_TO_WRAPPER_MAPPER.get(methodParameterTypes[i]) : methodParameterTypes[i];
            // Get a constructor for current parameter. The constructor signature should be the one that accepts String!
            Constructor<?> paramConstructor = parameterType.getConstructor(String.class);
            // Putt a instance of current parameter to the arguments array with parameterType.constructor(String);
            arguments[i] = paramConstructor.newInstance(params.get(i));
        }

        // here we are using concrete class AnimalCenterManager, (received by constructor)
        // to take the real object, who has all necessary methods from its interface
        // and invoking the current method from this concrete object.
        method.invoke(this.manager, arguments);

        // this app does not need to return anything according the initial description
        return null;
    }

    /*private void injectDependencies(Executable executable) throws IllegalAccessException {
        Field[] fieldsBaseCommand = executable.getClass().getSuperclass().getDeclaredFields();
        Field[] fieldsCommand = executable.getClass().getDeclaredFields();
        Field[] allFields = null;
        if (fieldsCommand.length > 0) {
            allFields = new Field[fieldsBaseCommand.length + fieldsCommand.length];
            System.arraycopy(fieldsBaseCommand, 0, allFields, 0, fieldsBaseCommand.length);
            System.arraycopy(fieldsCommand, 0, allFields, fieldsBaseCommand.length, fieldsCommand.length);
        }

        for (Field field : fieldsCommand.length > 0 ? allFields : fieldsBaseCommand) {
            if (field.isAnnotationPresent(Inject.class)) {
                Field[] currentFields = this.getClass().getDeclaredFields();
                for (Field currentField : currentFields) {
                    if (field.getType().equals(currentField.getType())) {
                        field.setAccessible(true);
                        field.set(executable, currentField.get(this));
                    }
                }
            }
        }
    }*/

    private void setClassMethod(String mainManagerClassName) throws ClassNotFoundException {
        // initialization of all methods for interface class
        Class<? extends Manager> managerClass = (Class<Manager>) Class.forName(COMMAND_CLASS_PATH + mainManagerClassName + COMMAND_CLASS_NAME_SUFFIX);
        Method[] managerMethods = managerClass.getMethods();
        for (Method managerMethod : managerMethods) {
            this.methods.putIfAbsent(managerMethod.getName(), managerMethod);
        }
    }
}
