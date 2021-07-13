package donnee;

import model.Etudiant;
import model.Promotion;

public class StubPromotion {

    public Promotion getPromotion() {
        Promotion promo = new Promotion();
        promo.ajouterEtudiant(new Etudiant("Toto1"));
        promo.ajouterEtudiant(new Etudiant("Toto2"));
        promo.ajouterEtudiant(new Etudiant("Toto3"));
        promo.ajouterEtudiant(new Etudiant("Toto4"));
        return promo;
    }
}
