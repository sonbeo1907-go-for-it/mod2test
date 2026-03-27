package persistance.fileManager;
import java.util.List;

public interface DataWriter<T> {
    void saveAll(String path, List<T> data);
    void append(String path, T item);
}
