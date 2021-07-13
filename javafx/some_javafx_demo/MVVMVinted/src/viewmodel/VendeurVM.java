package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Statut;
import model.Vendeur;
import model.Vetement;
import model.utils.Subscriber;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;

public class VendeurVM implements Subscriber {

    private Vendeur model;

    private final StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() { return pseudo.get(); }
        public StringProperty pseudoProperty() { return pseudo; }
        public void setPseudo(String pseudo) { this.pseudo.set(pseudo); }

    private final FloatProperty note = new SimpleFloatProperty();
        public float getNote() { return note.get(); }
        public FloatProperty noteProperty() { return note; }
        public void setNote(float note) { this.note.set(note); }

    private final IntegerProperty nbVente = new SimpleIntegerProperty();
        public int getNbVente() { return nbVente.get(); }
        public IntegerProperty nbVenteProperty() { return nbVente; }
        public void setNbVente(int nbVente) { this.nbVente.set(nbVente); }

    private final ObjectProperty<Statut> statut = new SimpleObjectProperty<>();
        public Statut getStatut() { return statut.get(); }
        public ObjectProperty<Statut> statutProperty() { return statut; }
        public void setStatut(Statut statut) { this.statut.set(statut); }

    private final ObservableList<VetementVM> lesVetementsObs = FXCollections.observableArrayList();
    private final ListProperty<VetementVM> lesVetements = new SimpleListProperty<>(lesVetementsObs);
        public ObservableList<VetementVM> getLesVetements() { return lesVetements.get(); }
        public ListProperty<VetementVM> lesVetementsProperty() { return lesVetements; }
        public void setLesVetements(ObservableList<VetementVM> lesVetements) { this.lesVetements.set(lesVetements); }

    public VendeurVM(Vendeur model) {
        this.model = model;
        subscribe(this.model);
        this.model.getLesVetements().forEach(vetement -> lesVetementsObs.add(new VetementVM(vetement)));
        initialisationPropriete();
    }

    private void initialisationPropriete() {
        pseudo.addListener((unused1, unused2, newPseudo) -> model.setPseudo(newPseudo));
        pseudo.set(model.getPseudo());
        note.set(model.getNote());
        nbVente.set(model.getNbVente());
        statut.set(model.getStatut());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Vendeur.PROP_NOM:
                pseudo.set( (String) evt.getNewValue());
                break;
            case Vendeur.PROP_VETEMENT_SUPPR:
                lesVetementsObs.remove(( (IndexedPropertyChangeEvent) evt).getIndex());
                break;
            case Vendeur.PROP_VETEMENT_AJOUTER:
                lesVetementsObs.add(( (IndexedPropertyChangeEvent) evt).getIndex(),
                        new VetementVM( (Vetement) evt.getNewValue()));
                break;
        }
    }

    public Vendeur getModel() {
        return model;
    }
}
