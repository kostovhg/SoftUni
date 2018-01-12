package app.factory;

import app.contracts.Action;
import app.contracts.ActionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionFactoryImpl extends BaseFactory<Action> implements ActionFactory {

    private static final String SUB_CLASS_NAME = "actions";

    public ActionFactoryImpl() {
        super();
    }

    @Override
    public Action create(String actionName, String... participantNames) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return super.create(SUB_CLASS_NAME, actionName);
//        Class<?> actionClass = super.getClass(actionName, SUB_CLASS_NAME);
//        Constructor constructor = actionClass.getConstructor();
//        return (Action) constructor.newInstance();
    }
}
