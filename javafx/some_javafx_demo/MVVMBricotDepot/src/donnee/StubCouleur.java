package donnee;

import model.Couleur;
import model.utils.RVB;

import java.util.ArrayList;
import java.util.List;

public abstract class StubCouleur {

    public static List<Couleur> getCouleurs() {
        List<Couleur> lesCouleurs = new ArrayList<>();
        lesCouleurs.add(new Couleur(new RVB(0.456, 0.0234, 0.9456)));
        lesCouleurs.add(new Couleur(new RVB(0.7342, 0.4532, 0.6)));
        lesCouleurs.add(new Couleur(new RVB(0.456, 0.6234, 0.456)));
        return lesCouleurs;
    }
}
