package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Etudiant;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EtudiantVM implements PropertyChangeListener {

    private Etudiant model;

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return this.nom; }

    public EtudiantVM(Etudiant etudiant) {
        this.model = etudiant;
        this.model.ajouterListener(this);
        this.nom.addListener((unused1, unused2, nom) -> this.model.setNom(nom));
        this.nom.set(etudiant.getNom());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case Etudiant.PROP_NOM:
                this.nom.set((String) evt.getNewValue());
                break;
        }
    }
}
