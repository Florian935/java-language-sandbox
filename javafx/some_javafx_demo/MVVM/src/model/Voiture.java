package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Voiture {

    private String marque;
    private String couleurRGB;
    private Couleur couleurVoiture;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public static final String PROP_COULEUR_RGB = "couleurRGB";
    public static final String PROP_MARQUE = "marque";
    public static final String PROP_COULEUR = "couleur";

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        String old = this.marque;
        this.marque = marque;
        support.firePropertyChange(PROP_MARQUE, old, this.marque);
    }

    public Couleur getCouleurVoiture() {
        return couleurVoiture;
    }

    public void setCouleur(Couleur couleur) {
        Couleur old = this.couleurVoiture;
        this.couleurVoiture = couleur;
        String rgb = new StringBuilder(" (R: ")
                .append(Math.round(couleur.getPalette().getRed()*255))
                .append(", G: ")
                .append(Math.round(couleur.getPalette().getGreen()*255))
                .append(", B: ")
                .append(Math.round(couleur.getPalette().getBlue()*255))
                .append(")")
                .toString();
        setCouleurRGB(rgb);
        this.support.firePropertyChange(PROP_COULEUR, old, this.couleurVoiture);
    }

    public String getCouleurRGB() {
        return couleurRGB;
    }

    public void setCouleurRGB(String couleurRGB) {
        String old = this.couleurRGB;
        this.couleurRGB = couleurRGB;
        support.firePropertyChange(PROP_COULEUR_RGB, old, this.couleurRGB);
    }

    public void ajouterListener(PropertyChangeListener evt) {
        support.addPropertyChangeListener(evt); }
}
