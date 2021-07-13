package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Promotion {

    private List<Etudiant> promo = new ArrayList<>();

    public void ajouterEtudiant(Etudiant e) {
        promo.add(e);
    }

    public List<Etudiant> getLesEtudiants() {
        return Collections.unmodifiableList(promo);
    }
}
