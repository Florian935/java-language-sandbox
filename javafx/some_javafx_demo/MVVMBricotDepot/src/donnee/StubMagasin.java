package donnee;

import model.Magasin;

import java.io.IOException;

public class StubMagasin implements Loader<Magasin> {

    @Override
    public Magasin load() {
        Magasin magasin = new Magasin();
        StubOutil.getOutils().forEach(magasin::ajouterOutil);
        return magasin;
    }
}
