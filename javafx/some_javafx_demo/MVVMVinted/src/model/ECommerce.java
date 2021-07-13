package model;

import model.utils.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ECommerce extends Subject implements Serializable {

    private static final Long serialVersionUID = 123L;
    private final List<Vendeur> lesVendeurs = new ArrayList<>();
    public transient static final String PROP_VENDEUR_SUPPR = "supprimer";
    public transient static final String PROP_VENDEUR_AJOUTER = "ajouter";

    public List<Vendeur> getLesVendeurs() {
        return Collections.unmodifiableList(lesVendeurs);
    }

    public void ajouterVendeur(Vendeur vendeur) {
        this.lesVendeurs.add(vendeur);
        int index = lesVendeurs.indexOf(vendeur);
        getSupport().fireIndexedPropertyChange(PROP_VENDEUR_AJOUTER, index, null, vendeur);
    }

    public void supprimerVendeur(int index) {
        lesVendeurs.remove(index);
        getSupport().fireIndexedPropertyChange(PROP_VENDEUR_SUPPR, index, null, index);
    }
}
