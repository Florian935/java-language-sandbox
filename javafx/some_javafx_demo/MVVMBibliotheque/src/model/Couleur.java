package model;

import model.utils.RVB;
import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Couleur implements Serializable, Subject {

    private static final long serialVersionUID = 123456L;
    private RVB couleur;
    private transient PropertyChangeSupport support;
    public static final String PROP_COULEUR = "couleur";

    public Couleur(double r, double v, double b) {
        initialisationCouleur(r, v, b);
        changerCouleur(r, v, b);
    }

    private void initialisationCouleur(double r, double v, double b) {
        couleur = new RVB();
        couleur.setRouge(r);
        couleur.setVert(v);
        couleur.setBleu(b);
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public RVB getCouleur() {
        return couleur;
    }

    public void changerCouleur(double r, double v, double b) {
        initialisationCouleur(r, v, b);
        getSupport().firePropertyChange(PROP_COULEUR, null, couleur);
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
