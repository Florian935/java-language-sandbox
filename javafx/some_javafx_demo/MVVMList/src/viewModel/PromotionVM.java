package viewModel;

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

    private ObservableList<EtudiantVM> lesEtudiantsVMobs = FXCollections.observableArrayList();
    private final ListProperty<EtudiantVM> lesEtudiantsVM = new SimpleListProperty<>(lesEtudiantsVMobs);
        public ReadOnlyListProperty<EtudiantVM> lesEtudiantsVMProperty() { return lesEtudiantsVM; }

    public PromotionVM(Promotion promo) {
        model = promo;
        model.addListener(this);
        model.getLesEtudiants().forEach(unEtudiant -> lesEtudiantsVMobs.add(new EtudiantVM(unEtudiant)));
    }

    public Promotion getModel() {
        return model;
    }

    public void ajouterEtudiant(String nom) {
        Etudiant e = new Etudiant();
        e.setNom(nom);
        model.ajouterEtudiant(e);
    }

    public void supprimerEtudiant(int selectedIndex) {
        model.supprimerEtudiant(selectedIndex);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Promotion.PROP_LES_ETUDIANTS:
                lesEtudiantsVMobs.add(
                        ((IndexedPropertyChangeEvent) evt).getIndex(),
                        new EtudiantVM((Etudiant) evt.getNewValue()));
                break;
            case Promotion.PROP_SUPPR_ETUDIANT:
                lesEtudiantsVMobs.remove((int)evt.getNewValue());
                break;
        }
    }
}

