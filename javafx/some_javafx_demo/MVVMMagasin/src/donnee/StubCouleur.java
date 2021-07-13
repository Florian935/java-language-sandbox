package donnee;

import model.Couleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StubCouleur implements Serializable {

    public List<Couleur> getCouleur() {
        List<Couleur> lesCouleurs = new ArrayList<>() {{
            add(new Couleur(0.4234, 0.7234, 0.2234));
            add(new Couleur(0.1245, 0.1523, 0.954));
            add(new Couleur(0.274, 0.4324, 0.73132));
            add(new Couleur(0.738590, 0.45652, 0.345345));
        }};
        return lesCouleurs;
    }
}
