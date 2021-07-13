package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Joueur {

    private StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() { return pseudo.get(); }
        public void setPseudo(String pseudo) { this.pseudo.set(pseudo); }
        public StringProperty pseudoProperty() { return pseudo; }

    private IntegerProperty scoreTotal = new SimpleIntegerProperty();
        public int getScoreTotal() { return scoreTotal.get(); }
        public void setScoreTotal(int score) { scoreTotal.set(score); }
        public IntegerProperty scoreTotalProperty() { return scoreTotal; }

    private IntegerProperty scoreEnCours = new SimpleIntegerProperty();
        public int getScoreEnCours() { return scoreEnCours.get(); }
        public void setScoreEnCours(int score) { scoreEnCours.set(score); }
        public IntegerProperty scoreEnCoursProperty() { return scoreEnCours; }

    public Joueur(String pseudo) {
        this.pseudo.set(pseudo);
    }
}
