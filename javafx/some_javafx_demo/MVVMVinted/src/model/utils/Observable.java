package model.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Observable {

    PropertyChangeSupport getSupport();

    default void ajouterListener(PropertyChangeListener listener) {
        getSupport().addPropertyChangeListener(listener);
    }

    default void supprimerListener(PropertyChangeListener listener) {
        getSupport().removePropertyChangeListener(listener);
    }
}
