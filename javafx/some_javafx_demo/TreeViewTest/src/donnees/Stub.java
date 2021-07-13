package donnees;

import modele.Etudiant;

import java.util.ArrayList;
import java.util.List;

public abstract class Stub {

    public static List<Etudiant> getLesEtudiants() {
        List<Etudiant> lesEtudiants = new ArrayList<>() {{
            add(new Etudiant("TOTO1", "toto1"));
            add(new Etudiant("TOTO2", "toto2"));
            add(new Etudiant("TOTO3", "toto3"));
            add(new Etudiant("TOTO4", "toto4"));
        }};
        return lesEtudiants;
    }
}
