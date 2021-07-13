package model;

import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.*;

public class Couleur implements Serializable, Observable {

    private Map<String, Double> couleur;
    private transient PropertyChangeSupport support;
    public static final String PROP_COULEUR = "couleur";
    private static final long serialVersionUID = -5886680556867205036L;

    public Couleur(double r, double v, double b) {
        this.couleur = new HashMap<>() {
            private static final long serialVersionUID = -864327854166580226L;

            {
            put("rouge", r);
            put("vert", v);
            put("bleu", b);
        }};
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public Map<String, Double> getCouleur() {
        return Collections.unmodifiableMap(couleur);
    }

    public void setCouleur(double r, double v, double b) {
        this.couleur = new HashMap<>() {
            private static final long serialVersionUID = -7506566712420726663L;

            {
            put("rouge", r);
            put("vert", v);
            put("bleu", b);
        }};
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
