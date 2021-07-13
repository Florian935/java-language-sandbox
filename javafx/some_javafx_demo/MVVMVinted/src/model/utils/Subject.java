package model.utils;

import java.beans.PropertyChangeSupport;

public abstract class Subject implements Observable {

    protected transient PropertyChangeSupport support;

    @Override
    public PropertyChangeSupport getSupport() {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        return support;
    }
}
