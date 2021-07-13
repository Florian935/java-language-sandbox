package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Bibliotheque;
import model.Livre;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BibliothequeVM implements PropertyChangeListener {

    private Bibliotheque model;

    private final ObservableList<LivreVM> lesLivresObs = FXCollections.observableArrayList();
    private final ListProperty<LivreVM> lesLivres = new SimpleListProperty<>(lesLivresObs);
        public ObservableList<LivreVM> getLesLivres() { return lesLivres.get(); }
        public void setLesLivres(ObservableList<LivreVM> lesLivres) { this.lesLivres.set(lesLivres); }
        public ReadOnlyListProperty<LivreVM> lesLivresProperty() { return lesLivres; }

    public BibliothequeVM(Bibliotheque model) {
        this.model = model;
        this.model.ajouterListener(this);
        this.model.getLesLivres().forEach(livre -> lesLivresObs.add(new LivreVM(livre)));
    }

    public Bibliotheque getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Bibliotheque.PROP_LIVRE:
                this.lesLivresObs.add(((IndexedPropertyChangeEvent)evt).getIndex(),
                        new LivreVM((Livre) evt.getNewValue()));
                break;
            case Bibliotheque.PROP_SUPPR:
                this.lesLivresObs.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }

    public void ajouterLivre(LivreVM livreVM) {
        model.ajouterLivre(livreVM.getModel());
    }

    public void supprimerLivre(int selectedIndex) {
        model.supprimerLivre(selectedIndex);
    }
}
