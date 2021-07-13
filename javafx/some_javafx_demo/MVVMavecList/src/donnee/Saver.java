package donnee;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Saver<T> {

    public void save(T toSave) throws FileNotFoundException, IOException;
}
