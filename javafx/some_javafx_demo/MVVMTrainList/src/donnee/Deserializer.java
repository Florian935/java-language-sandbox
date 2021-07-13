package donnee;

import model.Promotion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer implements Loader<Promotion> {
    @Override
    public Promotion load() throws ClassNotFoundException, IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.bin"));
        Promotion promotion =  ((Promotion)ois.readObject());
        return promotion;
    }
}
