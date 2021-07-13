package model;

import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meuble implements Serializable, Observable {

    private static final long serialVersionUID = 9003054665102428366L;
    public static final String PROP_NOM = "nom";
    private transient PropertyChangeSupport support;
    private String nom;
    private Etat etat;
    private int quantite;
    private float prix;
    private List<Couleur> lesCouleurs;

    public Meuble(String nom, Etat etat, int quantite, float prix, List<Couleur> lesCouleurs) {
        this.nom = nom;
        this.etat = etat;
        this.quantite = quantite;
        this.prix = prix;
        this.lesCouleurs = lesCouleurs;
    }

    public List<Couleur> getLesCouleurs() {
        return Collections.unmodifiableList(lesCouleurs);
    }

    public void setLesCouleurs(List<Couleur> lesCouleurs) {
        this.lesCouleurs = lesCouleurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        getSupport().firePropertyChange(PROP_NOM, old, nom);
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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
