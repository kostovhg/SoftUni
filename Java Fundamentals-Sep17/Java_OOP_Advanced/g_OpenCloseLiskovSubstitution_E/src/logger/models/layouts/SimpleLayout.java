package logger.models.layouts;

import logger.contracts.Layout;

/**
 * SimpleLayout for creating message in format
 * "date-time - level - message"
 */
public class SimpleLayout implements Layout {

    private static final String LAYOUT_FORMAT = "%s - %s - %s";

    @Override
    public String formatReport(String datetime, String reportLevel, String message) {
        return String.format(LAYOUT_FORMAT, datetime, reportLevel, message);
    }
}
