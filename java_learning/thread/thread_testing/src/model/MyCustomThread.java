package model;

public class MyCustomThread extends Thread {

    private final String nom;

    public MyCustomThread(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Ma troisième méthode. Nom: %s\n", nom);
            Thread.sleep(2000);
            System.out.println("Ma troisième méthode après Thread.sleep de la troisème méthode\n");
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }
}
