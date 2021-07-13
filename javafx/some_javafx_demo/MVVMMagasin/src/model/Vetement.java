package model;

import javafx.scene.paint.Color;
import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Vetement implements Serializable, Observable {

    private String nom;
    private float prix;
    private int quantite;
    private Taille taille;
    private List<Couleur> lesCouleurs;
    private transient PropertyChangeSupport support;
    public final static String PROP_QUANTITE = "quantite";
    public final static String PROP_COULEUR_SUPPR = "couleurSuppr";
    public final static String PROP_COULEUR_AJOUTE = "couleurAjoute";
    private static final long serialVersionUID = 2368159709838145652L;

    public Vetement(String nom, float prix, int quantite, Taille taille, List<Couleur> lesCouleurs) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.taille = taille;
        this.lesCouleurs = lesCouleurs;
    }

    @Override
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
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        int old = this.quantite;
        this.quantite = quantite;
        getSupport().firePropertyChange(PROP_QUANTITE, old, quantite);
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public List<Couleur> getLesCouleurs() {
        return Collections.unmodifiableList(lesCouleurs);
    }

    public void setLesCouleurs(List<Couleur> lesCouleurs) {
        this.lesCouleurs = lesCouleurs;
    }

    @Override
    public void ajouterListener(PropertyChangeListener listener) { getSupport().addPropertyChangeListener(listener); }

    @Override
    public void supprimerListener(PropertyChangeListener listener) { getSupport().removePropertyChangeListener(listener); }

    public void supprimerCouleur(int indexCouleur) {
        this.lesCouleurs.remove(indexCouleur);
        getSupport().fireIndexedPropertyChange(PROP_COULEUR_SUPPR, indexCouleur, null, indexCouleur);
    }

    public void ajouterCouleur(Color laCouleur) {
        Couleur couleur = new Couleur(laCouleur.getRed(), laCouleur.getGreen(), laCouleur.getBlue());
        lesCouleurs.add(couleur);
        int index = lesCouleurs.indexOf(couleur);
        getSupport().fireIndexedPropertyChange(PROP_COULEUR_AJOUTE, index,null, couleur);

    }
}
