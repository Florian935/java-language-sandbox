package model;

import model.utils.RVB;
import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Couleur implements Serializable, Subject {

    private static final Long serialVersionUID = 123L;
    private transient PropertyChangeSupport support;
    private RVB couleur;
    public transient static final String PROP_COULEUR = "couleur";

    public Couleur(RVB couleur) {
        this.couleur = couleur;
    }

    public RVB getCouleur() {
        return couleur;
    }

    public void changerCouleur(RVB couleur) {
        this.couleur = couleur;
        getSupport().firePropertyChange(PROP_COULEUR, null, couleur);
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    @Override
    public void ajouterListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    @Override
    public void supprimerListener(PropertyChangeListener listener) {
        getSupport().removePropertyChangeListener(listener);
    }
}
