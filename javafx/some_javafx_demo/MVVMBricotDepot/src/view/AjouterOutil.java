package view;

import donnee.StubCouleur;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Outil;
import model.Taille;
import viewmodel.OutilVM;

public class AjouterOutil {

    private OutilVM outilVM;

    @FXML
    private ComboBox<Taille> comboBox;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrix;
    @FXML
    private TextField textFieldQuantite;

    public void initialize() {
        comboBox.getItems().setAll(Taille.values());
    }

    public OutilVM getOutilVM() {
        return outilVM;
    }

    @FXML
    private void clicAjouter() {
        outilVM = new OutilVM(new Outil(
                textFieldNom.getText(),
                Float.parseFloat(textFieldPrix.getText()),
                Integer.parseInt(textFieldQuantite.getText()),
                comboBox.getSelectionModel().getSelectedItem(),
                StubCouleur.getCouleurs(),
                "resource/image/outil1.jpg"
        ));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        outilVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        comboBox.getScene().getWindow().hide();
    }
}
