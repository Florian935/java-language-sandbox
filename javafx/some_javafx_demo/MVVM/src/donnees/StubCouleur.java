package donnees;

import model.Couleur;

public abstract  class StubCouleur {

    public static Couleur getCouleur() {
        Couleur couleur = new Couleur(200, 200, 200);
        return couleur;
    }
}
