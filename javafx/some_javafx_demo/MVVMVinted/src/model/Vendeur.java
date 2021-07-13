package model;

import model.utils.Subject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Vendeur extends Subject implements Serializable {

    private static final Long serialVersionUID = 123L;
    private String pseudo;
    private float note;
    private int nbVente;
    private Statut statut;
    private final List<Vetement> lesVetements;
    public transient static final String PROP_NOM = "nom";
    public transient static final String PROP_VETEMENT_SUPPR = "supprimerVetement";
    public transient static final String PROP_VETEMENT_AJOUTER = "ajouterVetement";

    public Vendeur(String pseudo, float note, int nbVente, Statut statut, List<Vetement> lesVetements) {
        this.pseudo = pseudo;
        this.note = note;
        this.nbVente = nbVente;
        this.statut = statut;
        this.lesVetements = lesVetements;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        String old = this.pseudo;
        this.pseudo = pseudo;
        getSupport().firePropertyChange(PROP_NOM, old, pseudo);
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getNbVente() {
        return nbVente;
    }

    public void setNbVente(int nbVente) {
        this.nbVente = nbVente;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Vetement> getLesVetements() {
        return Collections.unmodifiableList(lesVetements);
    }

    public void ajouterVetement(Vetement vetement) {
        lesVetements.add(vetement);
        int index = lesVetements.indexOf(vetement);
        getSupport().fireIndexedPropertyChange(PROP_VETEMENT_AJOUTER, index, null, vetement);
    }

    public void supprimerVetement(int index) {
        lesVetements.remove(index);
        getSupport().fireIndexedPropertyChange(PROP_VETEMENT_SUPPR, index, null, index);
    }
}
