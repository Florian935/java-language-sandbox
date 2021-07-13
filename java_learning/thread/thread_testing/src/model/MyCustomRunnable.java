package model;

public class MyCustomRunnable implements Runnable {

    private final String nom;

    public MyCustomRunnable(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        System.out.printf("Ma seconde méthode. Nom: %s%n", nom);
    }

    public String getNom() {
        return nom;
    }
}
