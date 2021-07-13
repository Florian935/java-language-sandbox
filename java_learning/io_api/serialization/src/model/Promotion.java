package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Promotion implements Serializable {

    private List<Etudiant> lesEtudiants = new ArrayList<>();

    public List<Etudiant> getLesEtudiants() {
        return lesEtudiants;
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        this.lesEtudiants.add(etudiant);
    }
}
