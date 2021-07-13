package model;

import model.utils.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bibliotheque implements Serializable, Subject {


    private static final long serialVersionUID = 1123456L;
    public static final String PROP_SUPPR = "supprimer";
    private final List<Livre> lesLivres = new ArrayList<>();
    private transient PropertyChangeSupport support;
    public static final String PROP_LIVRE = "livre";

    public List<Livre> getLesLivres() {
        return Collections.unmodifiableList(lesLivres);
    }

    public void ajouterLivre(Livre livre) {
        this.lesLivres.add(livre);
        int index = lesLivres.indexOf(livre);
        getSupport().fireIndexedPropertyChange(PROP_LIVRE, index, null, livre);
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

    public void supprimerLivre(int selectedIndex) {
        lesLivres.remove(selectedIndex);
        getSupport().fireIndexedPropertyChange(PROP_SUPPR, selectedIndex, null, selectedIndex);
    }
}
