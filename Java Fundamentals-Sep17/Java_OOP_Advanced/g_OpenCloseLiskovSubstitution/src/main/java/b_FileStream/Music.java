package b_FileStream;

public class Music implements Streamable{

    private String artist;
    private String album;
    private int length;
    private int bytesSent;

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getBytesSent() {
        return bytesSent;
    }
}
