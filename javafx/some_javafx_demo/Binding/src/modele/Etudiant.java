package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Etudiant {

    private StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return this.nom; }

    private StringProperty prenom = new SimpleStringProperty();
        public String getPrenom() { return this.prenom.get(); }
        public void setPrenom(String prenom) { this.prenom.set(prenom); }
        public StringProperty prenomProperty() { return this.prenom; };

    public Etudiant(String nom, String prenom) {
        this.nom.set(nom);
        this.prenom.set(prenom);
    }
}
