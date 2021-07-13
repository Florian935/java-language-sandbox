package model;


import java.io.*;

public class Sauveur {

    public static void save(Etudiant etudiant) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("toto.bin"))) {
            oos.writeObject(etudiant.getNom());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static String read() {
        String prenom = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("toto.bin"))) {
            prenom = ((String)ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return prenom;
    }
}
