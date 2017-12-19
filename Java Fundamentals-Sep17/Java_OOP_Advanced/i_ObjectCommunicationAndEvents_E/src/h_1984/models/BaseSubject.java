package h_1984.models;

import h_1984.annotations.ObservableField;
import h_1984.contracts.Observable;
import h_1984.contracts.Observer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static h_1984.constants.PrimitiveClassesToWrapperClassesMapper.mapToWrapper;

public abstract class BaseSubject extends ConspiracyObject implements Observable {

    private List<Observer> observers = new ArrayList<>();
    @ObservableField
    private String name;

    protected BaseSubject(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public void setState(String property, String newValue) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<?> type = this.getClass();
        Field[] baseFields = this.getClass().getSuperclass().getDeclaredFields();
        Field[] currentFields = this.getClass().getDeclaredFields();
        Field[] allFields = null;
        if (currentFields.length > 0) {
            allFields = new Field[baseFields.length + currentFields.length];
            System.arraycopy(baseFields, 0, allFields, 0, baseFields.length);
            System.arraycopy(currentFields, 0, allFields, baseFields.length, currentFields.length);
        }

        for (Field field : currentFields.length > 0 ? allFields : baseFields) {
            if(!field.isAnnotationPresent(ObservableField.class)) continue;
            if (field.getName().equals(property) && !property.equals("id")) {
                Class<?> parameterType = field.getType();
                if (parameterType.isPrimitive()) {
                    parameterType = mapToWrapper(parameterType);
                }
                field.setAccessible(true);
                String oldValue = String.valueOf(field.get(this));
                Constructor<?> paramConstructor = parameterType.getConstructor(String.class);
                field.set(this, paramConstructor.newInstance(newValue));
                notifyAllObservers(property, oldValue, newValue);
                return;
            }
        }
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyAllObservers(String property, String oldValue, String newValue) {
        for (Observer observer : observers) {
            observer.update(this, property, oldValue, newValue);
        }
    }
}
