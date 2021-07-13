package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Etudiant;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EtudiantVM implements PropertyChangeListener {

    private Etudiant model = new Etudiant();

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return nom; }

    public EtudiantVM(String nom) {
        this.model.ajouterListener(this);
        this.nom.addListener((unused1, unused2, newValue) -> this.model.setNom(newValue));
        this.nom.set(nom);
    }

    public Etudiant getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case Etudiant.PROP_NOM:
                    this.nom.set((String) evt.getNewValue());
                    break;
            }
    }
}
