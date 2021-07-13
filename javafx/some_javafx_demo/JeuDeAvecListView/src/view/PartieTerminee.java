package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Joueur;

public class PartieTerminee {

    private Boolean rejouer = false;

    @FXML
    private Label lblPseudoJoueur;

    public void initialize() {

            Joueur joueur = FenetrePrincipale.joueurVictorieux;
            lblPseudoJoueur.setText(joueur.getPseudo() + " a gagné(e) !");
    }

    @FXML
    private void clicOui() {
        rejouer = true;
        fermerFenetre();
    }

    @FXML
    private void clicNon() {
        rejouer = false;
        fermerFenetre();
    }

    private void fermerFenetre() {
        lblPseudoJoueur.getScene().getWindow().hide();
    }

    public Boolean getRejouer() {
        return rejouer;
    }
}
