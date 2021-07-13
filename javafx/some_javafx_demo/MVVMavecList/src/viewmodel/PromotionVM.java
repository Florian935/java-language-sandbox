package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Etudiant;
import model.Promotion;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PromotionVM implements PropertyChangeListener {

    private Promotion model;

    private final ObservableList<EtudiantVM> lesEtudiantsObs = FXCollections.observableArrayList();
    private final ListProperty<EtudiantVM> lesEtudiants = new SimpleListProperty<>(lesEtudiantsObs);
        public ObservableList<EtudiantVM> getLesEtudiants() { return FXCollections.unmodifiableObservableList(lesEtudiants.get()); }
        public void setLesEtudiants(ObservableList<EtudiantVM> lesEtudiants) { this.lesEtudiants.set(lesEtudiants); }
        public ReadOnlyListProperty<EtudiantVM> lesEtudiantsProperty() { return lesEtudiants; }

    public PromotionVM(Promotion model) {
        this.model = model;
        this.model.addListener(this);
        this.model.getLesEtudiants().forEach(etudiant -> lesEtudiantsObs.add(new EtudiantVM(etudiant)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Promotion.PROP_LES_ETUDIANTS:
                this.lesEtudiantsObs.add(
                        ((IndexedPropertyChangeEvent) evt).getIndex(),
                        new EtudiantVM(((Etudiant) evt.getNewValue())));
                break;
            case Promotion.PROP_SUPPR_ETUDIANT:
                this.lesEtudiantsObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }

    public void ajouterEtudiant(String nom) {
        this.model.ajouterEtudiant(new Etudiant(nom));
    }

    public Promotion getModel() {
        return model;
    }

    public void supprimerEtudiant(int index) {
        model.supprimerEtudiant(index);
    }
}
