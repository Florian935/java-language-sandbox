package modele;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Promotion {

    private ObservableList<Etudiant> lesEtudiantsObs = FXCollections.observableArrayList();
    private ListProperty<Etudiant> lesEtudiants = new SimpleListProperty<>();
        public ObservableList<Etudiant> getLesEtudiants() { return lesEtudiantsObs; }
        public ListProperty<Etudiant> lesEtudiantsProperty() { return lesEtudiants; }

        public Promotion(List<Etudiant> lesEtudiants) {
            lesEtudiantsObs.addAll(lesEtudiants);
        }
}
