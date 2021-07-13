package donnees;

import modele.Etudiant;

import java.util.ArrayList;
import java.util.List;

public abstract class Stub {

    public static List<Etudiant> getLesEtudiants() {
        List<Etudiant> lesEtudiants = new ArrayList<>() {{
            add(new Etudiant("TOTO1", "Toto1"));
            add(new Etudiant("TOTO2", "Toto2"));
            add(new Etudiant("TOTO3", "Toto3"));
            add(new Etudiant("TOTO4", "Toto4"));
        }};
        return lesEtudiants;
    }
}
