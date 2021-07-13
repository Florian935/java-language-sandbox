package donnee;

import donnee.utils.Saver;
import model.Magasin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements Saver<Magasin> {
    @Override
    public void save(Magasin toSave) throws IOException {
        try  (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"))) {
            oos.writeObject(toSave);
        }
    }
}
