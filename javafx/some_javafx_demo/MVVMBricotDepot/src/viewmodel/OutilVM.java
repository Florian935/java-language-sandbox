package viewmodel;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import model.Couleur;
import model.Outil;
import model.Taille;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class OutilVM implements PropertyChangeListener {

    private final Outil model;

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty nomProperty() { return nom; }
        public void setNom(String nom) { this.nom.set(nom); }

    private final FloatProperty prix = new SimpleFloatProperty();
        public float getPrix() { return prix.get(); }
        public FloatProperty prixProperty() { return prix; }
        public void setPrix(float prix) { this.prix.set(prix); }

    private final IntegerProperty quantite = new SimpleIntegerProperty();
        public int getQuantite() { return quantite.get(); }
        public IntegerProperty quantiteProperty() { return quantite; }
        public void setQuantite(int quantite) { this.quantite.set(quantite); }

    private final ObjectProperty<Taille> taille = new SimpleObjectProperty<>();
        public Taille getTaille() { return taille.get(); }
        public ObjectProperty<Taille> tailleProperty() { return taille; }
        public void setTaille(Taille taille) { this.taille.set(taille); }

    private final ObservableList<CouleurVM> lesCouleursObs = FXCollections.observableArrayList();
    private final ListProperty<CouleurVM> lesCouleurs = new SimpleListProperty<>(lesCouleursObs);
    public ObservableList<CouleurVM> getLesCouleurs() { return lesCouleurs.get(); }
    public ListProperty<CouleurVM> lesCouleursProperty() { return lesCouleurs; }
    public void setLesCouleurs(ObservableList<CouleurVM> lesCouleurs) { this.lesCouleurs.set(lesCouleurs); }

    private final StringProperty url = new SimpleStringProperty();
        public String getUrl() { return url.get(); }
        public StringProperty urlProperty() { return url; }
        public void setUrl(String url) { this.url.set(url); }

    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() { return image.get(); }
        public ObjectProperty<Image> imageProperty() { return image; }
        public void setImage(Image image) { this.image.set(image); }

    public OutilVM(Outil model) {
        this.model = model;
        this.model.ajouterListener(this);
        initialisationPropriete();
    }

    private void initialisationPropriete() {
            this.nomProperty().addListener((unused1, unused2, newNom) -> this.model.setNom(newNom));
            this.nom.set(model.getNom());
            this.prix.set(model.getPrix());
            this.quantite.set(model.getQuantite());
            this.taille.set(model.getTaille());
            initialisationImage();
            model.getCouleurs().forEach(couleur -> lesCouleursObs.add(new CouleurVM(couleur)));
    }

    private void initialisationImage() {
        url.addListener((unused1, unused2, newV) -> {
            File file = new File(newV);
            image.set(new Image(file.toURI().toString()));
            model.setUrl(newV);

        });
        url.set(model.getUrl());
    }

    public Outil getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Outil.PROP_NOM:
                this.nom.set((String) evt.getNewValue());
                break;
            case Outil.PROP_URL:
                url.set((String) evt.getNewValue());
                break;
            case Outil.PROP_COULEUR:
                lesCouleursObs.add((new CouleurVM( (Couleur) evt.getNewValue())));
                break;
            case Outil.PROP_COULEUR_SUPPR:
                lesCouleursObs.remove((int) evt.getNewValue());
                break;
        }
    }
}
