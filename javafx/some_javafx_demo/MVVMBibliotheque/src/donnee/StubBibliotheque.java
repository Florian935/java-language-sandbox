package donnee;

import model.Bibliotheque;

public class StubBibliotheque implements Loader<Bibliotheque> {
    @Override
    public Bibliotheque load() {
        Bibliotheque bibliotheque = new Bibliotheque();
        StubLivre.getLivres().forEach(bibliotheque::ajouterLivre);
        return bibliotheque;
    }
}
