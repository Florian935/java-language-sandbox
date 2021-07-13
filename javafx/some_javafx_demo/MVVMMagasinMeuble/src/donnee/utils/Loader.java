package donnee.utils;

import java.io.IOException;

public interface Loader<T> {

    T load() throws ClassNotFoundException, IOException;
}
