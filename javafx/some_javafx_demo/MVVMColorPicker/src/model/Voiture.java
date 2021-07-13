package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Voiture {

    private Couleur couleurVoiture;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String PROP_COULEUR = "couleur";

    public Couleur getCouleurVoiture() {
        return couleurVoiture;
    }

    public void setCouleurVoiture(Couleur couleurVoiture) {
        Couleur old = this.couleurVoiture;
        this.couleurVoiture = couleurVoiture;
        support.firePropertyChange(PROP_COULEUR, old, this.couleurVoiture);
    }

    public void ajouterListener(PropertyChangeListener evt) {
        support.addPropertyChangeListener(evt);
    }
}
