package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.Vetement;
import model.utils.Subscriber;

import java.beans.PropertyChangeEvent;
import java.io.File;

public class VetementVM implements Subscriber {

    private final Vetement model;

    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public StringProperty nomProperty() { return nom; }
        public void setNom(String nom) { this.nom.set(nom); }

    private final StringProperty url = new SimpleStringProperty();
        public String getUrl() { return url.get(); }
        public StringProperty urlProperty() { return url; }
        public void setUrl(String url) { this.url.set(url); }

    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() { return image.get(); }
        public ObjectProperty<Image> imageProperty() { return image; }
        public void setImage(Image image) { this.image.set(image); }

    private final ObjectProperty<CouleurVM> couleur = new SimpleObjectProperty<>();
        public CouleurVM getCouleur() { return couleur.get(); }
        public ObjectProperty<CouleurVM> couleurProperty() { return couleur; }
        public void setCouleur(CouleurVM couleur) { this.couleur.set(couleur); }

    public VetementVM(Vetement model) {
        this.model = model;
        subscribe(this.model);
        initialisationPropriete(model);
    }

    private void initialisationPropriete(Vetement model) {
        this.nom.set(this.model.getNom());
        this.couleur.set(new CouleurVM(model.getCouleur()));
        this.url.set(model.getUrl());
        File file = new File(url.get());
        this.image.set(new Image(file.toURI().toString()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case Vetement.PROP_URL:
                    this.url.set( (String) evt.getNewValue());
                    File file = new File(url.get());
                    this.image.set(new Image(file.toURI().toString()));
                    break;
            }
    }

    public Vetement getModel() {
        return model;
    }
}
