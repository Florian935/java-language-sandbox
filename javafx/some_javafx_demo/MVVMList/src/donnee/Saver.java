package donnee;

import java.io.IOException;

public interface Saver<T> {
    public void save(T toSave) throws IOException;
}

