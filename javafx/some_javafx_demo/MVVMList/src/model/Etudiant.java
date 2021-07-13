package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Etudiant implements Serializable, Observable {

    private String nom;
    private transient PropertyChangeSupport support;
    public static final String PROP_ETUDIANT = "etudiant";

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        getSupport().firePropertyChange(PROP_ETUDIANT, old, nom);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }
}


