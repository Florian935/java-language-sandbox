package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Voiture {

    private String url;
    public transient static final String PROP_URL = "url";
    private transient PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Voiture(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        String old = this.url;
        this.url = url;
        support.firePropertyChange(PROP_URL, null, this.url);
    }

    public void ajouterListener(PropertyChangeListener evt) {
        support.addPropertyChangeListener(evt);
    }
}
