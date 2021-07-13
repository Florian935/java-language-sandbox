package viewmodel;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import model.Couleur;
import model.Voiture;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VoitureVM implements PropertyChangeListener {


    private Voiture model = new Voiture();

    private final ObjectProperty<Color> couleurVoiture = new SimpleObjectProperty<>();

    public Color getCouleurVoiture() {
        return couleurVoiture.get();
    }

    public ObjectProperty<Color> couleurVoitureProperty() {
        return couleurVoiture;
    }

    public void setCouleurVoiture(Color couleurVoiture) {
        this.couleurVoiture.set(couleurVoiture);
    }

    private final StringProperty marque = new SimpleStringProperty();

    public String getMarque() {
        return marque.get();
    }

    public void setMarque(String marque) {
        this.marque.set(marque);
    }

    public StringProperty marqueProperty() {
        return marque;
    }

    private final StringProperty couleurRGB = new SimpleStringProperty();

    public String getCouleurRGB() {
        return couleurRGB.get();
    }

    public StringProperty couleurRGBProperty() {
        return couleurRGB;
    }

    public void setCouleurRGB(String couleurRGB) {
        this.couleurRGB.set(couleurRGB);
    }

    public VoitureVM(String marque, Couleur couleur) {
        model.ajouterListener(this);
        this.marque.addListener((unused1, unused2, newValue) -> {
            model.setMarque(newValue);
        });
        this.marque.set(marque);

        this.couleurVoiture.addListener((unused1, unused2, newValue)
                -> model.setCouleur(new Couleur(
                newValue.getRed() * 255,
                newValue.getGreen() * 255,
                newValue.getBlue() * 255)
        ));
        Color maCouleur = couleur.getPalette();
        this.couleurVoiture.set(new Color(maCouleur.getRed(), maCouleur.getGreen(), maCouleur.getBlue(), 1));
    }

    public Voiture getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Voiture.PROP_MARQUE:
                this.marque.set((String) evt.getNewValue());
                break;
            case Voiture.PROP_COULEUR:
                this.couleurVoiture.set(((Couleur) evt.getNewValue()).getPalette());
                System.out.println("ModelVM "+((Couleur) evt.getNewValue()).getPalette().getRed());
                break;
            case Voiture.PROP_COULEUR_RGB:
                this.couleurRGB.set((String) evt.getNewValue());
        }
    }
}
