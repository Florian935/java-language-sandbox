package launch;

import modele.*;

public class Launcher {
    public static void main(String[] args) {
        Promotion promo = new Promotion();
        EtudiantSerieux es = new EtudiantSerieux();
        EtudiantMauvais em = new EtudiantMauvais();
        promo.ajouterEtudiant(es);
        promo.ajouterEtudiant(em);
        Batiment batA = new Batiment("Bat A");
        BatimentEnfant batB = new BatimentEnfant("Bat B", "en mousse t'es mauvais");
        promo.lesEtudiants.get(0).methode(batA);
        promo.lesEtudiants.get(1).methode(batB);

        

        /*
        En résumé:
            -On peut stocker, dans un attribut de type interface, n'importe quel type à partir du moment ou le
            type stocké implémente ladite interface. Très utile lorsqu'on a besoin d'appeler une méthode spécifique
            sur toutes les classes qui implémentent l'interface (exemple avec PropertyChangeListener).
            -De la même façon, on peut stocker dans un attribut parent n'importe quel enfant. Il suffit ensuite de
            caster pour récupérer les méthodes/attributs spécifiques à l'enfant si on en a besoin.
         */
    }
}
