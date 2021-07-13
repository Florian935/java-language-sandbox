package donnee;

import model.Outil;
import model.Taille;

import java.util.ArrayList;
import java.util.List;

public abstract class StubOutil {

    public static List<Outil> getOutils() {
        List<Outil> lesOutils = new ArrayList<>();
        lesOutils.add(new Outil("Clé de 12", 12.99f, 3, Taille.M, StubCouleur.getCouleurs(), "resource/image/outil1.jpg"));
        lesOutils.add(new Outil("Marteau", 9.99f, 2, Taille.S, StubCouleur.getCouleurs(), "resource/image/outil2.jpg"));
        lesOutils.add(new Outil("Tournevis", 5.85f, 1, Taille.XL, StubCouleur.getCouleurs(), "resource/image/outil3.jpg"));
        lesOutils.add(new Outil("Rateau", 32.99f, 7, Taille.L, StubCouleur.getCouleurs(), "resource/image/outil4.jpg"));
        return lesOutils;
    }
}
