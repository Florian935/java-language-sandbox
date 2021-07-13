package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Etudiant implements Serializable {

    private String nom;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public final static String PROP_NOM = "nom";

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        support.firePropertyChange(PROP_NOM, old, this.nom);
    }

    public void ajouterListener(PropertyChangeListener evt) { support.addPropertyChangeListener(evt); }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(nom);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        nom = ois.readObject() + ": coucou read";
    }
}
