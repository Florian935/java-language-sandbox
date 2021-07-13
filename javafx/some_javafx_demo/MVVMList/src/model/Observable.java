package model;

import java.beans.PropertyChangeListener;

public interface Observable {

    public void addListener(PropertyChangeListener evt);
}
