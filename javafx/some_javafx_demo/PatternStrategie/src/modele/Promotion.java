package modele;

import java.util.ArrayList;
import java.util.List;

public class Promotion {

    public List<Etudier> lesEtudiants = new ArrayList<>();

    public void ajouterEtudiant(Etudier e) {
        lesEtudiants.add(e);
    }
}
