package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Magasin;
import model.Meuble;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MagasinVM implements PropertyChangeListener {

    private Magasin model;

    private final ObservableList<MeubleVM> lesMeublesObs = FXCollections.observableArrayList();
    private final ListProperty<MeubleVM> lesMeubles = new SimpleListProperty<>(lesMeublesObs);
        public ObservableList<MeubleVM> getlesMeubles() { return lesMeubles.get(); }
        public void setLesMeubles(ObservableList<MeubleVM> lesMeubles) { this.lesMeubles.set(lesMeubles); }
        public ReadOnlyListProperty<MeubleVM> lesMeublesProperty() { return lesMeubles; }

    public MagasinVM(Magasin model) {
        this.model = model;
        this.model.ajouterListener(this);
        this.model.getLesMeubles().forEach(meuble -> lesMeublesObs.add(new MeubleVM(meuble)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Magasin.PROP_MEUBLE_AJOUTE:
                lesMeublesObs.add(((IndexedPropertyChangeEvent)evt).getIndex(),
                        (new MeubleVM((Meuble)evt.getNewValue())));
                break;
        }
    }

    public Magasin getModel() {
        return model;
    }

    public void ajouterMeuble(MeubleVM meubleVM) {
            model.ajouterMeuble(meubleVM.getModel());
    }
}
