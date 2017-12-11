package logger.contracts;

public interface File {

    void write(String message);

    int getSize();
}
