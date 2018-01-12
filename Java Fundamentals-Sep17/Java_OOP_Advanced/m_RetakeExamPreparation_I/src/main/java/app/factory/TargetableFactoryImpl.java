package app.factory;

import app.contracts.Targetable;
import app.contracts.TargetableFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TargetableFactoryImpl extends BaseFactory<Targetable> implements TargetableFactory {

    private static final String SUB_CLASS_NAME = "participants";

    public TargetableFactoryImpl() {
        super();
    }

    @Override
    public Targetable create(String name, String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Targetable targetable = super.create(SUB_CLASS_NAME, className);
        targetable.setName(name);
        return targetable;
    }
}
