package model;

import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Couleur implements Serializable, Observable {

    private static final long serialVersionUID = -1604088014588323298L;
    private transient PropertyChangeSupport support;
    private Map<String, Double> couleur;
    public static final String PROP_COULEUR = "couleur";

    public Couleur(double r, double v, double b) {
        couleur = new HashMap<>();
        couleur.put("rouge", r);
        couleur.put("vert", v);
        couleur.put("bleu", b);
    }

    public Map<String, Double> getCouleur() {
        return couleur;
    }

    public void setCouleur(double r, double v, double b) {
        couleur = new HashMap<>();
        couleur.put("rouge", r);
        couleur.put("vert", v);
        couleur.put("bleu", b);
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
