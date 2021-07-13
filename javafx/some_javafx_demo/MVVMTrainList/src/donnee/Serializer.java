package donnee;

import model.Promotion;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer implements Saver<Promotion> {
    @Override
    public void save(Promotion toSave) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.bin"));
        oos.writeObject(toSave);
    }
}
