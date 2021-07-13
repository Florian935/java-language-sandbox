package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Promotion implements Serializable, Observable {

    private List<Etudiant> lesEtudiants = new ArrayList<>();
    private transient PropertyChangeSupport support;
    public static final String PROP_LES_ETUDIANTS = "lesEtudiants";
    public static final String PROP_SUPPR_ETUDIANT = "etudiantSupprime";

    public List<Etudiant> getLesEtudiants() {
        return Collections.unmodifiableList(lesEtudiants);
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        lesEtudiants.add(etudiant);
        getSupport().fireIndexedPropertyChange(PROP_LES_ETUDIANTS, lesEtudiants.indexOf(etudiant), null, etudiant);
    }

    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    public void supprimerEtudiant(int selectedIndex) {
        Etudiant e = lesEtudiants.get(selectedIndex);
        lesEtudiants.remove(selectedIndex);
        getSupport().fireIndexedPropertyChange(PROP_SUPPR_ETUDIANT, selectedIndex, null, selectedIndex);
    }
}
