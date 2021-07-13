package donnee;

import model.Couleur;

import java.util.ArrayList;
import java.util.List;

public abstract class StubCouleur {

    public static List<Couleur> getCouleurs() {
        List<Couleur> lesCouleurs = new ArrayList<>();
        lesCouleurs.add(new Couleur(0.34, 0.9, 0.56));
        lesCouleurs.add(new Couleur(0.9834, 0.43, 0.56));
        lesCouleurs.add(new Couleur(0.1239, 0.4934, 0.654));
        return lesCouleurs;
    }
}
