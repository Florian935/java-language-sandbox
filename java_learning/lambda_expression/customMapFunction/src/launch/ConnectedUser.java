package launch;

public class ConnectedUser {

    private final String prenom;
    private final String nom;

    public ConnectedUser(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }
}
