package model.utils;

import java.beans.PropertyChangeListener;

public interface Subscriber extends PropertyChangeListener {

    default void subscribe(Observable obs) {
        obs.ajouterListener(this);
    }
}
