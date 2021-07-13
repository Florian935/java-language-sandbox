package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import model.Couleur;
import model.Taille;
import model.Vetement;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VetementVM implements PropertyChangeListener {

    private Vetement model;

    private StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return nom; }

    private FloatProperty prix = new SimpleFloatProperty();
        public float getPrix() { return prix.get(); }
        public void setPrix(float prix) { this.prix.set(prix); }
        public FloatProperty prixProperty() { return prix; }

    private IntegerProperty quantite = new SimpleIntegerProperty();
        public int getQuantite() { return quantite.get(); }
        public void setQuantite(int quantite) { this.quantite.set(quantite); }
        public IntegerProperty quantiteProperty() { return quantite; }

    private ObjectProperty<Taille> taille = new SimpleObjectProperty<>();
        public Taille getTaille() { return taille.get(); }
        public void setTaille(Taille taille) { this.taille.set(taille); }
        public ObjectProperty<Taille> tailleProperty() { return taille; }

    private final ObservableList<CouleurVM> lesCouleursObs = FXCollections.observableArrayList();
    private final ListProperty<CouleurVM> lesCouleurs = new SimpleListProperty<>(lesCouleursObs);
        public ObservableList<CouleurVM> getlesCouleurs() { return lesCouleurs.get(); }
        public void setLesCouleurs(ObservableList<CouleurVM> lesCouleurs) { this.lesCouleurs.set(lesCouleurs); }
        public ReadOnlyListProperty<CouleurVM> lesCouleursProperty() { return lesCouleurs; }

    public VetementVM(Vetement vetement) {
        this.model = vetement;
        this.model.ajouterListener(this);
        this.model.getLesCouleurs().forEach(couleur -> lesCouleursObs.add(new CouleurVM(couleur)));
        this.quantiteProperty().addListener((unused1, unused2, newV) -> this.model.setQuantite((int)newV));
        this.nom.set(model.getNom());
        this.taille.set(model.getTaille());
        this.quantite.set(model.getQuantite());
        this.prix.set(model.getPrix());
    }

    public Vetement getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case Vetement.PROP_QUANTITE:
                this.quantiteProperty().set( (int) evt.getNewValue());
                break;
            case Vetement.PROP_COULEUR_SUPPR:
                this.lesCouleursProperty().remove(((IndexedPropertyChangeEvent) evt).getIndex());
                break;
            case Vetement.PROP_COULEUR_AJOUTE:
                this.lesCouleursObs.add(((IndexedPropertyChangeEvent)evt).getIndex(),
                        new CouleurVM((Couleur)evt.getNewValue()));
                break;
        }
    }

    public void ajouterCouleur(Color laCouleur) {
        model.ajouterCouleur(laCouleur);
    }
}
