package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AjouterEtudiant {

    @FXML
    private TextField textFieldNom;
    private String nom;

    public String getNom() {
        return nom;
    }

    @FXML
    private void clicAjouterEtudiant() {
        nom = textFieldNom.getText();
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        nom = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldNom.getScene().getWindow().hide();
    }
}
