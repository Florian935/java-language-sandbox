package donnee;

import model.ECommerce;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements Saver<ECommerce> {
    @Override
    public void save(ECommerce toSave) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(toSave);
        }
    }
}
