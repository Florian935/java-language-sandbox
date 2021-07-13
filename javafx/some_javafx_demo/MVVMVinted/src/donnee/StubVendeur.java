package donnee;

import model.Statut;
import model.Vendeur;

import java.util.ArrayList;
import java.util.List;

public abstract class StubVendeur {

    public static List<Vendeur> getLesVendeurs() {
        List<Vendeur> lesVendeurs = new ArrayList<>();
        lesVendeurs.add(new Vendeur("Toto1", 4.3f, 4, Statut.PARTICULIER, StubVetement.getLesVetements()));
        lesVendeurs.add(new Vendeur("Toto2", 4.9f, 2, Statut.PROFFESSIONNEL, StubVetement.getLesVetements()));
        lesVendeurs.add(new Vendeur("Toto3", 3.1f, 1, Statut.PROFFESSIONNEL, StubVetement.getLesVetements()));
        lesVendeurs.add(new Vendeur("Toto4", 1.6f, 5, Statut.PARTICULIER, StubVetement.getLesVetements()));

        return lesVendeurs;
    }
}
