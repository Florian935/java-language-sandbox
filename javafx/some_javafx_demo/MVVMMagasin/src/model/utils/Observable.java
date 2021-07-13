package model.utils;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public interface Observable {

    public void ajouterListener(PropertyChangeListener listener);

    public void supprimerListener(PropertyChangeListener listener);

    public PropertyChangeSupport getSupport();
}
