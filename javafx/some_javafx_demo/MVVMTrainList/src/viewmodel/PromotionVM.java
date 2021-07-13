package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
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

    private ObservableList<EtudiantVM> lesEtudiantsVMObs = FXCollections.observableArrayList();
    private final ListProperty<EtudiantVM> lesEtudiantsVM = new SimpleListProperty<>(lesEtudiantsVMObs);
        public ObservableList<EtudiantVM> getLesEtudiantsVM() { return lesEtudiantsVM.get(); }
        public ReadOnlyListProperty lesEtudiantsVMProperty() { return lesEtudiantsVM; }
        public void setLesEtudiantsVM(ObservableList<EtudiantVM> lesEtudiants) { lesEtudiantsVM.set(lesEtudiants); }

    public PromotionVM(Promotion promotion) {
        this.model = promotion;
        this.model.ajouterListener(this);
        this.model.getLesEtudiants().forEach(etudiant -> lesEtudiantsVMObs.add(new EtudiantVM(etudiant)));
    }

    public Promotion getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Promotion.PROP_LES_ETUDIANTS:
                this.lesEtudiantsVMObs.add(((IndexedPropertyChangeEvent) evt).getIndex(),
                        new EtudiantVM((Etudiant) evt.getNewValue()));
                break;
        }
    }

    public void ajouterEtudiant(String nom) {
            this.model.ajouterEtudiant(new Etudiant(nom));
    }
}
