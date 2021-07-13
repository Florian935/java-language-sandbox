package donnee;

import model.ECommerce;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer implements Loader<ECommerce> {
    @Override
    public ECommerce load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"))) {
            return (ECommerce) ois.readObject();
        }
    }
}
