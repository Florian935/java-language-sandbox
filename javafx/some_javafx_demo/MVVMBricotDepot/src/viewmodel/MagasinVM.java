package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Magasin;
import model.Outil;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MagasinVM implements PropertyChangeListener {

    private final Magasin model;

    private final ObservableList<OutilVM> lesOutilsObs = FXCollections.observableArrayList();
    private final ListProperty<OutilVM> lesOutils = new SimpleListProperty<>(lesOutilsObs);
        public ObservableList<OutilVM> getLesOutils() { return lesOutils.get(); }
        public ListProperty<OutilVM> lesOutilsProperty() { return lesOutils; }
        public void setLesOutils(ObservableList<OutilVM> lesOutils) { this.lesOutils.set(lesOutils); }

    public MagasinVM(Magasin model) {
        this.model = model;
        this.model.ajouterListener(this);
        this.model.getLesOutils().forEach(outil -> lesOutilsObs.add(new OutilVM(outil)));
    }

    public Magasin getModel() {
        return model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case Magasin.PROP_OUTIL:
                    lesOutilsObs.add(((IndexedPropertyChangeEvent)evt).getIndex(),
                            new OutilVM((Outil) evt.getNewValue()));
                    break;
                case Magasin.PROP_OUTIL_SUPPR:
                    lesOutilsObs.remove((int) evt.getNewValue());
            }
    }

    public void ajouterOutil(OutilVM outilVM) {
        model.ajouterOutil(outilVM.getModel());
    }

    public void supprimerOutil(int index) {
            model.supprimerOutil(index);
    }
}
