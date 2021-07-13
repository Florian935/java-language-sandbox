package model;

import model.utils.Observable;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Magasin implements Observable, Serializable {

    private static final long serialVersionUID = 2679611227624058957L;
    private transient PropertyChangeSupport support;
    private List<Meuble> lesMeubles = new ArrayList<>();
    public static final String PROP_MEUBLE_AJOUTE = "meubleAjoute";

    public void ajouterMeuble(Meuble meuble) {
        lesMeubles.add(meuble);
        int index = lesMeubles.indexOf(meuble);
        getSupport().fireIndexedPropertyChange(PROP_MEUBLE_AJOUTE, index, null, meuble);
    }

    public List<Meuble> getLesMeubles() {
        return Collections.unmodifiableList(lesMeubles);
    }

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        };
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
}
