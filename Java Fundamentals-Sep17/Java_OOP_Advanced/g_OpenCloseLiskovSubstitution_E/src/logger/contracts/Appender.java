package logger.contracts;

import logger.enums.ReportLevel;

/**
 *  Interface for appender that will append the formatted from layout message
 *  to concrete destination (eg. Console, File, etc)
 */
public interface Appender {

    /**
     * Append generated message
     * @param datetime
     * @param reportLevel
     * @param message
     */
    void append(String datetime, String reportLevel, String message);

    /**
     * Check if string representation of the message is presented in ReportLevel enumerations
     * @param messageLevel
     * @return
     */
    boolean isMessageValidLevel(String messageLevel);

    /**
     * Set the report level of concrete appender
     * @param reportLevelThreshold
     */
    void setReportLevelThreshold(ReportLevel reportLevelThreshold);

    /**
     * @return ReportLevel for current message
     */
    ReportLevel getReportLevelThreshold();

}
