package donnee;

import model.Etudiant;
import model.Promotion;

public abstract class StubPromotion {

    public static Promotion getPromotion() {
        Promotion promotion = new Promotion();
        promotion.ajouterEtudiant(new Etudiant("TOTO1", "toto1"));
        promotion.ajouterEtudiant(new Etudiant("TOTO2", "toto2"));
        promotion.ajouterEtudiant(new Etudiant("TOTO3", "toto3"));
        return promotion;
    }
}
