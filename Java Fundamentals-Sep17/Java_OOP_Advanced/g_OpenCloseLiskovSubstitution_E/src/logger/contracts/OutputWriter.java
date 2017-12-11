package logger.contracts;

public interface OutputWriter {
    void writeLine(String output);

    void writeLine(String format, Object... params);
}
