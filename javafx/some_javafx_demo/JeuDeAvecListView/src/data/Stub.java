package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Joueur;

import java.util.ArrayList;
import java.util.List;

public abstract class Stub {

    public static List<Joueur> getJoueurs() {
        List<Joueur> lesJoueurs = new ArrayList<>() {{
            add(new Joueur("Joueur 1"));
            add(new Joueur("Joueur 2"));
        }};
        return lesJoueurs;
    }
}
