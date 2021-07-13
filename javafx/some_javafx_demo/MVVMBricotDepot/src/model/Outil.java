package model;

import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Outil implements Serializable, Subject {


    private static final long serialVersionUID = 123L;
    private String nom;
    private float prix;
    private int quantite;
    private Taille taille;
    private final List<Couleur> couleurs;
    private String url;
    private transient PropertyChangeSupport support;
    public transient static final String PROP_NOM = "nom";
    public transient static final String PROP_URL = "url";
    public transient static final String PROP_COULEUR = "couleur";
    public transient static final String PROP_COULEUR_SUPPR = "couleurSuppr";

    public Outil(String nom, float prix, int quantite, Taille taille, List<Couleur> couleurs, String url) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.taille = taille;
        this.couleurs = couleurs;
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        getSupport().firePropertyChange(PROP_NOM, old, nom);
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
        this.quantite = quantite;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

    public List<Couleur> getCouleurs() {
        return Collections.unmodifiableList(couleurs);
    }

    public void AjouterCouleur(Couleur couleur) {
        this.couleurs.add(couleur);
    }

    public void supprimerCouleur(int index) {
        this.couleurs.remove(index);
        getSupport().firePropertyChange(PROP_COULEUR_SUPPR, null, index);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        String old = this.url;
        this.url = url;
        getSupport().firePropertyChange(PROP_URL, old, url);
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

    public void ajouterCouleur(Couleur model) {
        couleurs.add(model);
        getSupport().firePropertyChange(PROP_COULEUR, null, model);
    }
}
