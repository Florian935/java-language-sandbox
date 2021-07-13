package donnee;

import model.Livre;
import model.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class StubLivre {

    public static List<Livre> getLivres() {
        List<Livre> lesLivres = new ArrayList<>();
        lesLivres.add(new Livre("Livre 1", 13.99f, 3, Type.grand, StubCouleur.getCouleurs()));
        lesLivres.add(new Livre("Livre 2", 5.99f, 5, Type.numerique, StubCouleur.getCouleurs()));
        lesLivres.add(new Livre("Livre 3", 23.99f, 1, Type.grand, StubCouleur.getCouleurs()));
        lesLivres.add(new Livre("Livre 4", 30.99f, 7, Type.pocket, StubCouleur.getCouleurs()));
        return lesLivres;
    }
}
