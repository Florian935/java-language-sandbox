package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AjouterEtudiant {

    private String nom;

    @FXML
    private TextField textField;

    @FXML
    private void clicAjouter() {
        nom = textField.getText();
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        nom = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        textField.getScene().getWindow().hide();
    }

    public String getNom() {
        return nom;
    }
}
