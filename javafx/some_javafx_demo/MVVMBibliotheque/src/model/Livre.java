package model;

import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Livre implements Serializable, Subject {

    private static final long serialVersionUID = 123456L;
    public static final String PROP_SUPPR_COULEUR = "supprimerCouleur";
    private String titre;
    private float prix;
    private int nombreExemplaire;
    private Type type;
    private final List<Couleur> lesCouleurs;
    private transient PropertyChangeSupport support;
    public static final String PROP_TITRE = "titre";
    public static final String PROP_COULEUR = "couleur";

    public Livre(String titre, float prix, int nombreExemplaire, Type type, List<Couleur> lesCouleurs) {
        this.titre = titre;
        this.prix = prix;
        this.nombreExemplaire = nombreExemplaire;
        this.type = type;
        this.lesCouleurs = lesCouleurs;
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        String old = this.titre;
        this.titre = titre;
        getSupport().firePropertyChange(PROP_TITRE, old, titre);
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNombreExemplaire() {
        return nombreExemplaire;
    }

    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Couleur> getLesCouleurs() {
        return Collections.unmodifiableList(lesCouleurs);
    }

    public void ajouterCouleur(Couleur couleur) {
        this.lesCouleurs.add(couleur);
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

    public void supprimerCouleur(int index) {
        lesCouleurs.remove(index);
        getSupport().fireIndexedPropertyChange(PROP_SUPPR_COULEUR, index, null, index);
    }
}
