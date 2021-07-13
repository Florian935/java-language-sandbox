package donnee;

import model.Bibliotheque;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer implements Loader<Bibliotheque> {
    @Override
    public Bibliotheque load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))) {
            return (Bibliotheque) ois.readObject();
        }
    }
}
