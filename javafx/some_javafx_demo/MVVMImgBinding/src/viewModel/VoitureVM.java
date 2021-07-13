package viewModel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import model.Voiture;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

public class VoitureVM implements PropertyChangeListener {

    private Voiture model;

    private final ObjectProperty<Image> image = new SimpleObjectProperty<>();
        public Image getImage() { return image.get(); }
        public ObjectProperty<Image> imageProperty() { return image; }
        public void setImage(Image image) { this.image.set(image); }

    public VoitureVM(Voiture model) {
        this.model = model;
        model.ajouterListener(this);
        this.image.addListener((unused1, unused2, newValue) -> this.model.setUrl(newValue.getUrl()));
        File file = new File(model.getUrl());
        this.image.set(new Image(file.toURI().toString()));
    }

    public Voiture getModel() {
        return this.model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()) {
            case Voiture.PROP_URL:
                File file = new File((String) evt.getNewValue());
                this.image.set(new Image(file.toURI().toString()));
                break;
        }
    }
}
