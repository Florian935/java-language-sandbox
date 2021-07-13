package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Etudiant implements Serializable {

    private String nom;
    private transient PropertyChangeSupport support;
    public static final String PROP_NOM = "nom";

    public Etudiant(String nom) {
        this.nom = nom;
    }

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        getSupport().firePropertyChange(PROP_NOM, old, nom);
    }

    public void ajouterListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }
}
