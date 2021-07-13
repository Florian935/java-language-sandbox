package viewModel;

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
    public StringProperty nomProperty() { return nom; }

    public EtudiantVM(Etudiant etudiant) {
        this.model = etudiant;
        this.model.addListener(this);
        this.nom.addListener((unused1, unused2, newValue) -> this.model.setNom(newValue));
        this.nom.set(model.getNom());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Etudiant.PROP_ETUDIANT)) {
            this.nom.set((String)evt.getNewValue());
        }
    }
}
