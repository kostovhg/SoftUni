package logger.models.loggers;

import logger.contracts.Appender;
import logger.contracts.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MessageLogger is responsible to
 */
public class MessageLogger implements Logger {

    private List<Appender> appenderList;

    public MessageLogger(List<Appender> appenderList) {
        this.appenderList = appenderList;
    }

    public void log(String datetime, String reportLevel, String message) {
        for (Appender appender : appenderList) {
            appender.append(datetime, reportLevel, message);
        }
    }

    @Override
    public void logError(String dateTime, String message) {
        this.log(dateTime, "ERROR", message);
    }

    @Override
    public void logInfo(String dateTime, String message) {
        this.log(dateTime, "INFO", message);
    }

    @Override
    public void logFatal(String dateTime, String message) {
        this.log(dateTime, "FATAL", message);
    }

    @Override
    public void logCritical(String dateTime, String message) {
        this.log(dateTime, "CRITICAL", message);
    }

    @Override
    public void logWarning(String dateTime, String message) {
        this.log(dateTime, "WARNING", message);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Logger info").append(System.lineSeparator());
        for (Appender appender : this.appenderList) {
            sb.append(appender).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
