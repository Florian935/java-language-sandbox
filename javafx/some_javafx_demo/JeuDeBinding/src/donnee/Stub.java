package donnee;

import modele.Joueur;

import java.util.ArrayList;
import java.util.List;

public abstract class Stub {

    public static List<Joueur> getLesJoueurs() {
        List<Joueur> lesJoueurs = new ArrayList<>() {{
            add(new Joueur("Joueur 1"));
            add(new Joueur("Joueur 2"));
        }};
        return lesJoueurs;
    }
}
