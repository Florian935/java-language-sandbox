package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    private StringProperty dateVictoire = new SimpleStringProperty();
        public String getDateVictoire() { return dateVictoire.get(); }
        public StringProperty dateVictoireProperty() { return dateVictoire; }

    public Joueur(String pseudo) {
        this.pseudo.set(pseudo);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        dateVictoire.set(formatter.format(dateTime));
    }
}
