package modele;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Equipe {

    private ObservableList<Joueur> lesJoueursObs = FXCollections.observableArrayList();
    private ListProperty<Joueur> lesJoueurs = new SimpleListProperty<>();
        public ObservableList<Joueur> getLesJoueurs() { return lesJoueursObs; }
        public ListProperty<Joueur> lesJoueursProperty() { return lesJoueurs; }
        public void setLesJoueurs(ObservableList<Joueur> lesJoueurs) { lesJoueursObs.addAll(lesJoueurs); }

    public Equipe(ObservableList<Joueur> lesJoueursObs) {
        this.setLesJoueurs(lesJoueursObs);
    }
}
