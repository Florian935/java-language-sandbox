package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Etudiant;

public class AjouterEtudiant {

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtFieldNom;

    @FXML
    private TextField txtFieldPrenom;

    private Etudiant etudiantCreer;

    @FXML
    private void clicAjouter() {
        if (!txtFieldNom.getText().equals("") && !txtFieldPrenom.getText().equals("")) {
            etudiantCreer = new Etudiant(txtFieldNom.getText(), txtFieldPrenom.getText());
            fermerFenetre();
        }
    }

    @FXML
    private void clicAnnuler() {
        fermerFenetre();
    }

    private void fermerFenetre() {
        btnAjouter.getScene().getWindow().hide();
    }

    public Etudiant getEtudiantCreer() {
        return etudiantCreer;
    }
}
