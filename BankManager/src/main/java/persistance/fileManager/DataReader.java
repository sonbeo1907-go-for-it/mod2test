package persistance.fileManager;

import java.util.List;

public interface DataReader<T> {
    List<T> read(String path);
}
