package donnee;

import model.Couleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class StubCouleur {

    public static List<Couleur> getLesCouleurs() {
        List<Couleur> lesCouleurs = new ArrayList<>() {{
            add(new Couleur(0.12, 0.74, 0.9));
            add(new Couleur(0.23, 0.78, 0.2));
            add(new Couleur(0.89, 0.74, 0.623));
        }};
        return Collections.unmodifiableList(lesCouleurs);
    }
}
