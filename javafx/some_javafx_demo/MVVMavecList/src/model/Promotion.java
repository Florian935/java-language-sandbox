package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Promotion implements Serializable, Observable{

    private List<Etudiant> lesEtudiants = new ArrayList<>();
    private PropertyChangeSupport support;
    public static final String PROP_SUPPR_ETUDIANT = "supprEtudiant";
    public static final String PROP_LES_ETUDIANTS = "lesEtudiants";


    public void ajouterEtudiant(Etudiant e) {
        lesEtudiants.add(e);
        int index = lesEtudiants.indexOf(e);
        getSupport().fireIndexedPropertyChange(PROP_LES_ETUDIANTS, index, null, e);
    }

    public List<Etudiant> getLesEtudiants() {
        return Collections.unmodifiableList(lesEtudiants);
    }

    private PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);

    }

    public void supprimerEtudiant(int index) {
        lesEtudiants.remove(index);
        getSupport().fireIndexedPropertyChange(PROP_SUPPR_ETUDIANT, index, null, index);
    }
}
