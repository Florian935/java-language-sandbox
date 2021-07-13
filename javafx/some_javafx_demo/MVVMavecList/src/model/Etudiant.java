package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Etudiant implements Serializable, Observable {

    private String nom;
    private PropertyChangeSupport support;
    public static final String PROP_NOM = "nom";

    public Etudiant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        getSupport().firePropertyChange(PROP_NOM, old, nom);
    }

    private PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }
}
