package model.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Observable {

    PropertyChangeSupport getSupport();

    void ajouterListener(PropertyChangeListener listener);

    void supprimerListener(PropertyChangeListener listener);
}
