package logger.models.appenders;

import logger.annotation.AppenderType;
import logger.contracts.File;
import logger.contracts.Layout;

/**
 * Concrete appender for append message to the console
 */
@AppenderType (type = "console")
public class ConsoleAppender extends BaseAppender {

    /**
     * Constructor
     * @param layout - specific layout for format the messages
     */
    public ConsoleAppender(Layout layout) {
        super(layout);
    }

    /**
     * Using current layout to print message to the console
     * @param datetime
     * @param reportLevel
     * @param message
     */
    @Override
    public void append(String datetime, String reportLevel, String message) {
        if (this.isMessageValidLevel(reportLevel)) {
            System.out.println(this.layout.formatReport(datetime, reportLevel, message));
            this.messagesCount++;
        }
    }
}
