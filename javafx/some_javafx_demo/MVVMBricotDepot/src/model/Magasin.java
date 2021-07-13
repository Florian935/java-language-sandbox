package model;

import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Magasin implements Serializable, Subject {

    private static final Long serialVersionUID = 123L;
    private transient PropertyChangeSupport support;
    private List<Outil> lesOutils = new ArrayList<>();
    public transient static final String PROP_OUTIL = "outil";
    public transient static final String PROP_OUTIL_SUPPR = "supprime";


    public List<Outil> getLesOutils() {
        return Collections.unmodifiableList(lesOutils);
    }

    public void ajouterOutil(Outil outil) {
        this.lesOutils.add(outil);
        int index = lesOutils.indexOf(outil);
        getSupport().fireIndexedPropertyChange(PROP_OUTIL, index, null, outil);
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

    public void supprimerOutil(int index) {
        lesOutils.remove(index);
        getSupport().firePropertyChange(PROP_OUTIL_SUPPR, null, index);
    }
}
