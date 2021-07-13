package donnee;

import java.io.IOException;

public interface Loader<T> {

    public T load() throws ClassNotFoundException, IOException;
}
