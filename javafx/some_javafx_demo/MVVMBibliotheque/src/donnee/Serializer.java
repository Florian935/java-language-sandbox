package donnee;

import model.Bibliotheque;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements Saver<Bibliotheque> {
    @Override
    public void save(Bibliotheque toSave) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(toSave);
        }
    }
}
