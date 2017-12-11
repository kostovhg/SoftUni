package logger.controllers;

import logger.contracts.Appender;
import logger.contracts.Layout;
import logger.contracts.File;
import logger.contracts.Logger;
import logger.enums.ReportLevel;
import logger.models.*;
import logger.models.appenders.ConsoleAppender;
import logger.models.appenders.FileAppender;
import logger.models.layouts.SimpleLayout;
import logger.models.layouts.XmlLayout;

import java.util.Date;
/*
public class SimpleCodeManualTester {

    public static void ReportThreshold() {
        Layout simpleLayout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(simpleLayout);
        consoleAppender.setReportLevelThreshold(ReportLevel.ERROR);

        Logger logger = new MessageLogger(consoleAppender);

        logger.logInfo("3/31/2015 5:33:07 PM", "Everything seems fine");
        logger.logWarning("3/31/2015 5:33:07 PM", "Warning: ping is too high - disconnect imminent");
        logger.logError("3/31/2015 5:33:07 PM", "Error parsing request");
        logger.logCritical("3/31/2015 5:33:07 PM", "No connection string fount in App.config");
        logger.logFatal("3/31/2015 5:33:07 PM", "mscorlib.dll does not respond");

    }

    public static void extensibility() {
        Layout xmlLayout = new XmlLayout();
        Appender consoleAppender = new ConsoleAppender(xmlLayout);
        Logger logger = new MessageLogger(consoleAppender);

        logger.logFatal("3/31/2015 5:33:07 PM", "mscorlib.dll does not respond");
        logger.logCritical("3/31/2015 5:33:07 PM", "No connection string fount in App.config");
    }

    public static void implementations() {
        Layout simpleLayout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(simpleLayout);
        File file = new LogFile();

        Appender fileAppender = new FileAppender(simpleLayout);
        //((FileAppender) fileAppender).setFile(file);
        Logger logger = new MessageLogger(consoleAppender, fileAppender);
        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
        logger.logError("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");
    }

    public static void sampleInput() {
        Layout simpleLayout = new SimpleLayout();
        Appender consoleAppender = new ConsoleAppender(simpleLayout);
        Logger logger = new MessageLogger(consoleAppender);

        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
        logger.logError("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");

        // second try
        Date time = new Date();
        consoleAppender.setReportLevelThreshold(ReportLevel.ERROR);

        logger.logInfo(time.toString(), "Kvo stana?");
    }
}

*/
