package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Promotion implements Serializable {

    private List<Etudiant> lesEtudiants = new ArrayList<>();
    private transient PropertyChangeSupport support;
    public static final String PROP_LES_ETUDIANTS = "lesEtudiants";

    public List<Etudiant> getLesEtudiants() {
        return Collections.unmodifiableList(lesEtudiants);
    }

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        this.lesEtudiants.add(etudiant);
        int index = lesEtudiants.indexOf(etudiant);
        getSupport().fireIndexedPropertyChange(PROP_LES_ETUDIANTS, index, null, etudiant);
    }

    public void ajouterListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }
}
