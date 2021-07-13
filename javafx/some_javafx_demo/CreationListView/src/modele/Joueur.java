package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Joueur {

    private StringProperty nom = new SimpleStringProperty();
        public String getNom() { return nom.get(); }
        public void setNom(String nom) { this.nom.set(nom); }
        public StringProperty nomProperty() { return nom; }

    private StringProperty prenom = new SimpleStringProperty();
        public String getPrenom() { return prenom.get(); }
        public void setPrenom(String prenom) { this.prenom.set(prenom); }
        public StringProperty prenomProperty() { return prenom; }


    public Joueur(String nom, String prenom) {
            this.nom.set(nom);
            this.prenom.set(prenom);
    }
}
