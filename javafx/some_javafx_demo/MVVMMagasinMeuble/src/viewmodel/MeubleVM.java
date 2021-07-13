package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.Etat;
import model.Meuble;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MeubleVM implements PropertyChangeListener {

    private Meuble model;

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty nomProperty() { return nom; }
        public void setNom(String nom) { this.nom.set(nom); }

    private final ObjectProperty<Etat> etat = new SimpleObjectProperty<>();
        public Etat getEtat() { return etat.get(); }
        public ObjectProperty<Etat> etatProperty() { return etat; }
        public void setEtat(Etat etat) { this.etat.set(etat); }

    private final IntegerProperty quantite = new SimpleIntegerProperty();
        public int getQuantite() { return quantite.get(); }
        public IntegerProperty quantiteProperty() { return quantite; }
        public void setQuantite(int quantite) { this.quantite.set(quantite); }

    private final FloatProperty prix = new SimpleFloatProperty();
        public float getPrix() { return prix.get(); }
        public FloatProperty prixProperty() { return prix; }
        public void setPrix(float prix) { this.prix.set(prix); }

    private final ObservableList<CouleurVM> lesCouleursObs = FXCollections.observableArrayList();
    private final ListProperty<CouleurVM> lesCouleurs = new SimpleListProperty<>(lesCouleursObs);
        public ObservableList<CouleurVM> getLesCouleurs() { return lesCouleurs.get(); }
        public ReadOnlyListProperty<CouleurVM> lesCouleursProperty() { return lesCouleurs; }
        public void setLesCouleurs(ObservableList<CouleurVM> lesCouleurs) { this.lesCouleurs.set(lesCouleurs); }

    public MeubleVM(Meuble model) {
        this.model = model;
        this.model.ajouterListener(this);
        initialisationPropriete(model);
        this.model.getLesCouleurs().forEach(couleur -> lesCouleursObs.add(new CouleurVM(couleur)));
    }

    private void initialisationPropriete(Meuble model) {
        this.nom.addListener((unused1, unused2, newNom) -> this.model.setNom(newNom));
        this.nom.set(model.getNom());
        this.quantite.set(model.getQuantite());
        this.prix.set(model.getPrix());
        this.etat.set(model.getEtat());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Meuble.PROP_NOM:
                this.nom.set((String) (evt.getNewValue()));
                break;
        }
    }

    public Meuble getModel() {
        return model;
    }
}
