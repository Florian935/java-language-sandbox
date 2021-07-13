package donnees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modele.Joueur;

public abstract class Stub {

    public static ObservableList<Joueur> getLesJoueurs() {
        return FXCollections.observableArrayList(
                new Joueur("TOTO1", "Joueur1"),
                new Joueur("TOTO2", "Joueur2"),
                new Joueur("TOTO3", "Joueur3"),
                new Joueur("TOTO4", "Joueur4")
        );
    }
}
