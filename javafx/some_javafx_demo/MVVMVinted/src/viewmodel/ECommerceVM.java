package viewmodel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ECommerce;
import model.Vendeur;
import model.utils.Subscriber;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;

public class ECommerceVM implements Subscriber {

    private final ECommerce model;

    private final ObservableList<VendeurVM> lesVendeursObs = FXCollections.observableArrayList();
    private final ListProperty<VendeurVM> lesVendeurs = new SimpleListProperty<>(lesVendeursObs);
        public ObservableList<VendeurVM> getLesVendeurs() { return lesVendeurs.get(); }
        public ListProperty<VendeurVM> lesVendeursProperty() { return lesVendeurs; }
        public void setLesVendeurs(ObservableList<VendeurVM> lesVendeurs) { this.lesVendeurs.set(lesVendeurs); }

    public ECommerceVM(ECommerce model) {
        this.model = model;
        subscribe(this.model);
        this.model.getLesVendeurs().forEach(vendeur -> lesVendeursObs.add(new VendeurVM(vendeur)));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case ECommerce.PROP_VENDEUR_SUPPR:
                lesVendeursObs.remove(( (IndexedPropertyChangeEvent) evt).getIndex());
                break;
            case ECommerce.PROP_VENDEUR_AJOUTER:
                lesVendeursObs.add(( (IndexedPropertyChangeEvent) evt).getIndex(),
                        new VendeurVM( (Vendeur) evt.getNewValue()));
        }
    }

    public ECommerce getModel() {
        return model;
    }

    public void supprimerVendeur(int index) {
        model.supprimerVendeur(index);
    }

    public void ajouterVendeur(VendeurVM vendeurVM) {
        model.ajouterVendeur(vendeurVM.getModel());
    }
}
