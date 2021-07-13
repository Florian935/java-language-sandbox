package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Couleur;
import model.Livre;
import model.Type;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LivreVM implements PropertyChangeListener {

    private Livre model;

    private final StringProperty titre = new SimpleStringProperty();
        public String getTitre() { return titre.get(); }
        public void setTitre(String titre) { this.titre.set(titre); }
        public StringProperty titreProperty() { return titre; }

    private final FloatProperty prix = new SimpleFloatProperty();
        public float getPrix() { return prix.get(); }
        public FloatProperty prixProperty() { return prix; }
        public void setPrix(float prix) { this.prix.set(prix); }

    private final IntegerProperty nombreExemplaire = new SimpleIntegerProperty();
        public int getNombreExemplaire() { return nombreExemplaire.get(); }
        public IntegerProperty nombreExemplaireProperty() { return nombreExemplaire; }
        public void setNombreExemplaire(int nombreExemplaire) { this.nombreExemplaire.set(nombreExemplaire); }

    private final ObjectProperty<Type> type = new SimpleObjectProperty<>();
        public Type getType() { return type.get(); }
        public ObjectProperty<Type> typeProperty() { return type; }
        public void setType(Type type) { this.type.set(type); }

    private final ObservableList<CouleurVM> lesCouleursObs = FXCollections.observableArrayList();
    private final ListProperty<CouleurVM> lesCouleurs = new SimpleListProperty<>(lesCouleursObs);
        public ObservableList<CouleurVM> getLesCouleurs() { return lesCouleurs.get(); }
        public ListProperty<CouleurVM> lesCouleursProperty() { return lesCouleurs; }
        public void setLesCouleurs(ObservableList<CouleurVM> lesCouleurs) { this.lesCouleurs.set(lesCouleurs); }

    public LivreVM(Livre model) {
        this.model = model;
        this.model.ajouterListener(this);
        initialisationPropriete(model);
    }

    private void initialisationPropriete(Livre model) {
        this.titre.addListener((unused1, unused2, newValue) -> this.model.setTitre(newValue));
        this.titre.set(model.getTitre());
        this.prix.set(model.getPrix());
        this.nombreExemplaire.set(model.getNombreExemplaire());
        this.type.set(model.getType());
        this.model.getLesCouleurs().forEach(couleur -> lesCouleursObs.add(new CouleurVM(couleur)));
    }

    public Livre getModel() {
            return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Livre.PROP_TITRE:
                this.titre.set((String) evt.getNewValue());
                break;
            case Livre.PROP_COULEUR:
                this.lesCouleursObs.add((new CouleurVM((Couleur) evt.getNewValue())));
                break;
            case Livre.PROP_SUPPR_COULEUR:
                this.lesCouleursObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }
}
