package logger.contracts;

import java.util.List;

public interface Logger {

    void log(String datetime, String level, String message);

    void logError(String datetime, String parsingMethod);

    void logInfo(String datetime, String parsingMethod);

    void logFatal(String datetime, String parsingMethod);

    void logCritical(String datetime, String parsingMethod);

    void logWarning(String ditetime, String parsingMethod);
}
