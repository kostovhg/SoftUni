package logger.models.appenders;

import logger.contracts.Appender;
import logger.contracts.Layout;
import logger.enums.ReportLevel;

/**
 * Base class for Appenders that holds the common fields
 */
public abstract class BaseAppender implements Appender {

    /**
     * All appenders will have:
     * - one Layout that is going to format the each message
     * - reportLevelThreshold a threshold for messages to be appended
     * - messageCount that holds the count of generated msg's
     */
    Layout layout;
    private ReportLevel reportLevelThreshold;
    int messagesCount;

    /**
     * Common Constructor for all Appenders
     *
     * @param layout
     */
    BaseAppender(Layout layout) {
        this.layout = layout;
        // temporally take INFO as default Level
        this.reportLevelThreshold = ReportLevel.values()[0];
    }

    /**
     * Change the report level threshold of the appender
     *
     * @param reportLevelThreshold
     */
    public void setReportLevelThreshold(ReportLevel reportLevelThreshold) {
        this.reportLevelThreshold = reportLevelThreshold;
    }

    /**
     * @return the current report level threshold
     */
    public ReportLevel getReportLevelThreshold() {
        return this.reportLevelThreshold;
    }

    /**
     * Abstract method that should append message to concrete destination
     *
     * @param datetime
     * @param reportLevel
     * @param message
     */
    public abstract void append(String datetime, String reportLevel, String message);

    /**
     * Check if the string representation of the report level is valid
     *
     * @param messageLevel - a string representation of report level
     * @return - boolean (true if messageLevel is correct)
     */
    public boolean isMessageValidLevel(String messageLevel) {
        try {
            ReportLevel threshold = ReportLevel.valueOf(messageLevel);
            return this.reportLevelThreshold.ordinal() <= threshold.ordinal();
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }

    /**
     * @return string representation of all appenders
     */
    @Override
    public String toString() {
        return String.format("Appender type: %s, Layout type: %s, Report level: %s, Messages appended: %s",
                this.getClass().getSimpleName(), this.layout.getClass().getSimpleName(),
                this.reportLevelThreshold.toString(), this.messagesCount);
    }
}
