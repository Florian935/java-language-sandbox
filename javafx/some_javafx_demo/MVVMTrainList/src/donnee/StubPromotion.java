package donnee;

import model.Etudiant;
import model.Promotion;

public class StubPromotion {

    public Promotion getPromotion() {
        Promotion promotion = new Promotion();
        promotion.ajouterEtudiant(new Etudiant("Toto1"));
        promotion.ajouterEtudiant(new Etudiant("Toto2"));
        promotion.ajouterEtudiant(new Etudiant("Toto3"));
        return promotion;
    }
}
