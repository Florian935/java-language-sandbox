package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Magasin;
import model.Taille;
import model.Vetement;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MagasinVM implements PropertyChangeListener {

    private Magasin model;

    private final IntegerProperty stockTotal = new SimpleIntegerProperty();
        public int getStockTotal() { return stockTotal.get(); }
        public void setStockTotal(int stockTotal) { this.stockTotal.set(stockTotal); }
        public IntegerProperty stockTotalProperty() { return stockTotal; }

    private final ObservableList<VetementVM> lesVetementsObs = FXCollections.observableArrayList();
    private final ListProperty<VetementVM> lesVetements = new SimpleListProperty<>(lesVetementsObs);
        public ObservableList<VetementVM> getLesVetements() { return lesVetements.get(); }
        public void setLesVetements(ObservableList<VetementVM> lesVetements) { this.lesVetements.set(lesVetements); }
        public ReadOnlyListProperty<VetementVM> lesVetementsProperty() { return lesVetements; }

    private final ObservableList<Taille> lesTaillesObs = FXCollections.observableArrayList();
    private final ListProperty<Taille> lesTailles = new SimpleListProperty<>(lesTaillesObs);
        public ObservableList<Taille> getLesTailles() { return lesTailles.get(); }
        public ReadOnlyListProperty<Taille> lesTaillesProperty() { return lesTailles; }

    public MagasinVM(Magasin model) {
            this.model = model;
            this.model.ajouterListener(this);
            this.model.getLesVetements().forEach(vetement -> {
                lesVetementsObs.add(new VetementVM(vetement));
                stockTotal.set(stockTotal.get() + vetement.getQuantite());
            });
            lesTaillesObs.addAll(Taille.XS, Taille.S, Taille.M, Taille.L, Taille.XL);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case Magasin.PROP_VETEMENTS:
                this.lesVetementsObs.add(((IndexedPropertyChangeEvent)evt).getIndex(),
                        new VetementVM(((Vetement)evt.getNewValue())));
                break;
            case Magasin.PROP_VETEMENTS_SUPPR:
                this.lesVetementsObs.remove(((IndexedPropertyChangeEvent) evt).getIndex());
        }
    }

    public Magasin getModel() {
        return model;
    }

    public void ajouterVetement(VetementVM vetementVM) { model.ajouterVetement(vetementVM.getModel()); }

    public void supprimerVetement(Vetement model) {
        this.model.supprimerVetement(model);
    }

    public void supprimerCouleur(int indexVetement, int indexCouleur) {
            this.model.getLesVetements().get(indexVetement).supprimerCouleur(indexCouleur);
    }
}
