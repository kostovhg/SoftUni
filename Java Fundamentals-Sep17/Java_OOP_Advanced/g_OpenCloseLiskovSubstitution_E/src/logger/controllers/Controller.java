package logger.controllers;

import logger.contracts.Appender;
import logger.contracts.Layout;
import logger.enums.ReportLevel;

import java.lang.reflect.InvocationTargetException;

public class Controller {

    private static final String LAYOUT_PACKAGE_PATH = "logger.models.layouts.";
    public static final String LOGGER_FILE_PATH = "logger.models.appenders.";

    @SuppressWarnings("unchecked")
    public Appender readAppender(String[] input) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String layoutType = input[1];
        Class<Layout> layoutClass = (Class<Layout>) Class.forName(LAYOUT_PACKAGE_PATH + layoutType);
        Layout layout = layoutClass.newInstance();

        String appenderType = input[0];
        Class<Appender> appenderClass = (Class<Appender>) Class.forName(LOGGER_FILE_PATH + appenderType);
        Appender appender = appenderClass.getDeclaredConstructor(Layout.class).newInstance(layout);

        if (input.length > 2) {
            appender.setReportLevelThreshold(ReportLevel.valueOf(input[2]));
        }

        return appender;
    }
}
