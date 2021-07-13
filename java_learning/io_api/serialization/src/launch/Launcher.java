package launch;

import donnee.StubPromotion;
import model.Promotion;

import java.io.*;

public class Launcher {

    public static void main(String[] args) {
        Promotion promo = StubPromotion.getPromotion();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("toto.bin"))) {
            oos.writeObject(promo);
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("toto.bin"))) {
            Promotion promoRead = ((Promotion)ois.readObject());
            promoRead.getLesEtudiants().forEach(e -> System.out.println(e.getNom()));
        } catch(IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
