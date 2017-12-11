package logger.models;

import logger.contracts.File;

public class LogFile implements File {

    private final StringBuilder sb;
    private int size;

    public LogFile(){
        this.sb = new StringBuilder();
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void write(String message) {
        this.sb.append(message);
        this.size += message.chars().filter(Character::isLetter).sum();
    }

    @Override
    public String toString() {
        return this.sb.toString().trim();
    }
}
