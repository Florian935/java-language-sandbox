package donnee;

import model.Magasin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer implements Loader<Magasin> {

    @Override
    public Magasin load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))) {
            return (Magasin) ois.readObject();
        }
    }
}
