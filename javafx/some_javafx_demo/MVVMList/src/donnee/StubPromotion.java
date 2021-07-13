package donnee;

import model.Etudiant;
import model.Promotion;

public class StubPromotion {

    public Promotion getPromotion() {
        Promotion promo = new Promotion();
        Etudiant e1 = new Etudiant();
        Etudiant e2 = new Etudiant();
        Etudiant e3 = new Etudiant();
        e1.setNom("Toto1");
        e2.setNom("Toto2");
        e3.setNom("Toto3");
        promo.ajouterEtudiant(e1);
        promo.ajouterEtudiant(e2);
        promo.ajouterEtudiant(e3);
        return promo;
    }
}
