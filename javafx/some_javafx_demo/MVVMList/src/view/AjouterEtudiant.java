package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import viewModel.EtudiantVM;

public class AjouterEtudiant extends HBox {

    private String nom;

    @FXML
    private TextField textFieldNom;

    public String getNom() {
        return nom;
    }

    @FXML
    private void clicAnnuler() {
        nom = null;
        fermerFenetre();
    }

    @FXML
    private void clicAjouter() {
        nom = textFieldNom.getText();
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldNom.getScene().getWindow().hide();
    }
}
