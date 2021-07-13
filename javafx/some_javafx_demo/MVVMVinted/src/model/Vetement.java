package model;

import model.utils.Subject;

import java.io.Serializable;

public class Vetement extends Subject implements Serializable {

    private static final Long serialVersionUID = 123L;
    private String nom;
    private Couleur couleur;
    private String url;
    public transient static final String PROP_URL = "url";

    public Vetement(String nom, Couleur couleur, String url) {
        this.nom = nom;
        this.couleur = couleur;
        this.url = url;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        String old = this.url;
        this.url = url;
        getSupport().firePropertyChange(PROP_URL, old, url);
    }
}
