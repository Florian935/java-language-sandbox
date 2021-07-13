package donnee;

import model.Couleur;
import model.Vetement;
import model.utils.RVB;

import java.util.ArrayList;
import java.util.List;

public abstract class StubVetement {

    public static List<Vetement> getLesVetements() {
        List<Vetement> lesVetements = new ArrayList<>();
        lesVetements.add(new Vetement("Pull", new Couleur(new RVB(0.23423, 0.983, 0.435)), "resource/image/1.jpg"));
        lesVetements.add(new Vetement("Sweat", new Couleur(new RVB(0.89234, 0.83, 0.12335)), "resource/image/2.jpg"));
        lesVetements.add(new Vetement("Jean", new Couleur(new RVB(0.423, 0.5673, 0.556)), "resource/image/3.jpg"));
        lesVetements.add(new Vetement("Pantalon", new Couleur(new RVB(0.423, 0.393, 0.7865)), "resource/image/4.jpg"));
        return lesVetements;
    }
}
