package model;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Partie {

    private De de;
    private List<Joueur> lesJoueurs;

    private ObservableList<Joueur> joueursObs = FXCollections.observableArrayList();
    private ReadOnlyListProperty<Joueur> joueurs = new SimpleListProperty<>(joueursObs);
        public ObservableList<Joueur> getJoueursObs() { return joueursObs; }
        public ReadOnlyListProperty<Joueur> joueursProperty() { return joueurs; }
        public void setJoueursObs(List<Joueur> joueurs) { joueursObs.addAll(joueurs); }

    public Partie(List<Joueur> lesJoueurs) {
            joueursObs.addAll(lesJoueurs);
    }
}
