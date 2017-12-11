package logger.models.layouts;

import logger.contracts.Layout;

public class XmlLayout implements Layout {
    @Override
    public String formatReport(String datetime, String reportLevel, String message) {
        return String.format("<log>\n\t<date>%s</date>\n" +
                        "\t<level>%s</level>\n\t<message>%s</message>\n</log>"
                , datetime, reportLevel, message);
    }
}
