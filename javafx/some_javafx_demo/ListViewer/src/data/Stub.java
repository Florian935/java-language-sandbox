package data;

import modele.Etudiant;

import java.util.ArrayList;
import java.util.List;

public abstract class Stub {

    public static List<Etudiant> getPromo() {
        List<Etudiant> lesEtudiants = new ArrayList<>() {{
            add(new Etudiant("DUPONT", "Toto1"));
            add(new Etudiant("MARTIN", "Toto2"));
            add(new Etudiant("DUPUY", "Toto3"));
            add(new Etudiant("FERRAUD", "Toto4"));
        }};
        return lesEtudiants;
    }
}
