package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import model.Couleur;
import model.Voiture;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VoitureVM implements PropertyChangeListener {

    private Voiture model = new Voiture();

    private final ObjectProperty<Color> couleurVoiture = new SimpleObjectProperty<>();
        public Color getCouleurVoiture() { return couleurVoiture.get(); }
        public void setCouleurVoiture(Color color) { couleurVoiture.set(color); }
        public ObjectProperty<Color> couleurVoitureProperty() { return couleurVoiture; }

    public VoitureVM(Couleur couleur) {
        this.model.ajouterListener(this);
        couleurVoiture.addListener((unused1, unused2, newValue) ->
            this.model.setCouleurVoiture(new Couleur(
                    newValue.getRed() * 255,
                    newValue.getGreen() * 255,
                    newValue.getBlue() * 255
            ))
        );
        Color color = couleur.getColorPalette();
        this.couleurVoiture.set(color);
    }

    public Voiture getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            switch(evt.getPropertyName()) {
                case Voiture.PROP_COULEUR:
                    this.couleurVoiture.set(((Couleur)evt.getNewValue()).getColorPalette());
                    break;
            }
    }
}
