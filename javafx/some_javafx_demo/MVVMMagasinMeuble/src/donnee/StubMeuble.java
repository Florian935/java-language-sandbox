package donnee;

import model.Etat;
import model.Meuble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class StubMeuble {

    public static List<Meuble> getLesMeubles() {
        List<Meuble> lesMeubles = new ArrayList<>() {{
            add(new Meuble("Buffet", Etat.ABIME, 3, 3.02f, StubCouleur.getLesCouleurs()));
            add(new Meuble("Bibliothèque", Etat.NEUF, 4, 30.75f, StubCouleur.getLesCouleurs()));
            add(new Meuble("Table basse", Etat.OCCASION, 2, 17.99f, StubCouleur.getLesCouleurs()));
            add(new Meuble("Meuble TV", Etat.NEUF, 5, 90.10f, StubCouleur.getLesCouleurs()));
        }};
        return Collections.unmodifiableList(lesMeubles);
    }
}
