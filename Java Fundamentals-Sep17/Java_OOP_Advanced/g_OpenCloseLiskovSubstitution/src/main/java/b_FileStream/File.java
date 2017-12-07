package b_FileStream;

public class File implements Streamable{

    private String name;
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
