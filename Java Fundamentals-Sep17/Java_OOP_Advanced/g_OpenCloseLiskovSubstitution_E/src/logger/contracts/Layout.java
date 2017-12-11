package logger.contracts;

/**
 *  Interface for layouts
 *  Different layouts will generate differently formatted messages
 */
public interface Layout {

    /**
     * @param datetime      - String representing datetime in message
     * @param reportLevel   - String representation of report level
     * @param message       - String: the message
     * @return String formatted according concrete implementation
     */
    String formatReport(String datetime, String reportLevel, String message);

}
