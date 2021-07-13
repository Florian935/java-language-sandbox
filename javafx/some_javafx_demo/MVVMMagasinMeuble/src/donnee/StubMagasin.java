package donnee;

import donnee.utils.Loader;
import model.Magasin;
import model.Meuble;

import java.util.List;

public class StubMagasin implements Loader<Magasin> {
    @Override
    public Magasin load() {
        Magasin magasin = new Magasin();
        List<Meuble> lesMeubles = StubMeuble.getLesMeubles();
        lesMeubles.forEach(magasin::ajouterMeuble);
        return magasin;
    }
}
