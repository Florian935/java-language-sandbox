package model;

import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Magasin implements Serializable, Observable {

    private List<Vetement> lesVetements = new ArrayList<>();
    private transient PropertyChangeSupport support;
    public static final String PROP_VETEMENTS = "lesVetements";
    public static final String PROP_VETEMENTS_SUPPR = "vetementSuppr";
    private static final long serialVersionUID = -1662555809220068780L;


    public List<Vetement> getLesVetements() {
        return Collections.unmodifiableList(lesVetements);
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }

    public void ajouterVetement(Vetement vetement) {
        lesVetements.add(vetement);
        int index = lesVetements.indexOf(vetement);
        getSupport().fireIndexedPropertyChange(PROP_VETEMENTS, index, null, vetement);
    }

    @Override
    public void ajouterListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    @Override
    public void supprimerListener(PropertyChangeListener listener) { getSupport().removePropertyChangeListener(listener); }

    public void supprimerVetement(Vetement model) {
        int index = lesVetements.indexOf(model);
        lesVetements.remove(index);
        getSupport().fireIndexedPropertyChange(PROP_VETEMENTS_SUPPR, index, null, model);
    }
}
