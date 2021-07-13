package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modele.Joueur;

public class AjouterJoueur {

    @FXML
    TextField textFieldPseudo;

    @FXML
    TextField textFieldNom;

    private Joueur joueurCree;

    @FXML
    private void clicAjouter() {
        if (!textFieldNom.getText().equals("") && !textFieldPseudo.getText().equals("")) {
            joueurCree = new Joueur(textFieldNom.getText(), textFieldPseudo.getText());
        }
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldNom.getScene().getWindow().hide();
    }

    public Joueur getJoueurCree() {
        return joueurCree;
    }
}
