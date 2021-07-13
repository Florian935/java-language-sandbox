package donnee.utils;

import java.io.IOException;

public interface Saver<T> {

    void save(T toSave) throws IOException;
}
