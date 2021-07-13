package model;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Etudiant {

    private String nom;
    private String prenom;
    private Integer age;

    public Etudiant(String nom, String prenom, Integer age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Integer getAge() {
        System.out.println(this.age);
        return this.age;
    }

    public String getNomPrenom(String lieu) {
        System.out.println(new StringBuilder("Nom: ")
                .append(nom)
                .append(", Prenom: ")
                .append(prenom)
                .append(", Lieu: ")
                .append(lieu));
        return new StringBuilder("Nom: ")
                .append(nom)
                .append(", Prenom: ")
                .append(prenom).toString();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public <T> T getInformationAboutEtudiant(Etudiant e, Function<Etudiant, T> etudiantResolver) {
        return etudiantResolver.apply(e);
    }

    public void forEach(List<Etudiant> etudiants, Consumer<Etudiant> action) {
        for (Etudiant e : etudiants)
            action.accept(e);
    }
}
