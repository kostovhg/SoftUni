package logger.models.appenders;

import logger.annotation.AppenderType;
import logger.contracts.Layout;
import logger.contracts.File;
import logger.models.LogFile;

/**
 * Appender that write messages in a file
 */
@AppenderType(type = "file")
public class FileAppender extends BaseAppender {

    /**
     * File field that contains custom file witch will call
     */
    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();
    }

    @Override
    public void append(String datetime, String reportLevel, String message) {
        if (this.isMessageValidLevel(reportLevel)) {
            String report = this.layout.formatReport(datetime, reportLevel, message);
            this.file.write(report);
            this.messagesCount++;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, File size: %d", super.toString(), this.file.getSize());
    }
}
