package donnee;

import model.Magasin;
import model.Taille;
import model.Vetement;

public class StubMagasin implements Loader<Magasin> {

    @Override
    public Magasin load() {
        Magasin magasin = new Magasin();
        magasin.ajouterVetement(new Vetement("Pull", 12.99f, 3, Taille.S, new StubCouleur().getCouleur()));
        magasin.ajouterVetement(new Vetement("Short", 5.99f, 2, Taille.L, new StubCouleur().getCouleur()));
        magasin.ajouterVetement(new Vetement("Tee shirt", 30.99f, 7, Taille.XS, new StubCouleur().getCouleur()));
        magasin.ajouterVetement(new Vetement("Jean", 45.00f, 1, Taille.XL, new StubCouleur().getCouleur()));
        return magasin;
    }
}
