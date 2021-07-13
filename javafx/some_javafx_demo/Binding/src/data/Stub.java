package data;

import modele.Etudiant;

import java.util.ArrayList;
import java.util.List;

public class Stub {

    public static List<Etudiant> getPromo() {
        List<Etudiant> promo = new ArrayList<>() {{
            add(new Etudiant("DUPONT", "Henry"));
            add(new Etudiant("MARTIN", "Coco"));
            add(new Etudiant("JEAN", "Pilou"));
            add(new Etudiant("PAUL", "Thierry"));
        }};
        return promo;
    }
}
