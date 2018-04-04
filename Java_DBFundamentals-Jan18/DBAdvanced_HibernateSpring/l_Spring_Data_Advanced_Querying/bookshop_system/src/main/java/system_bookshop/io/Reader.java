package system_bookshop.io;

import java.io.IOException;
import java.util.List;

public interface Reader {
    List<String> read(String file) throws IOException;
}
