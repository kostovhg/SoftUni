package logger.enums;

/**
 *  Enumerator for messages report level with weight
 */
public enum ReportLevel {
    INFO(1), WARNING(2), ERROR(3), CRITICAL(4), FATAL(5);

    private final int level;

    ReportLevel(int level){
        this.level = level;
    }
}
