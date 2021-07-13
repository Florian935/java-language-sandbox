package view;

import donnee.StubVetement;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Statut;
import model.Vendeur;
import viewmodel.VendeurVM;

public class AjouterVendeur {

    @FXML
    private TextField textFieldPseudo;
    @FXML
    private TextField textFieldNbVente;
    @FXML
    private TextField textFieldNote;
    @FXML
    private ComboBox<Statut> comboBox;

    private VendeurVM vendeurVM;

    public void initialize() {
        comboBox.getItems().setAll(Statut.values());
    }

    public VendeurVM getVendeurVM() {
        return vendeurVM;
    }

    @FXML
    private void clicAjouter() {
        vendeurVM = new VendeurVM(new Vendeur(
                textFieldPseudo.getText(),
                Float.parseFloat(textFieldNote.getText()),
                Integer.parseInt(textFieldNbVente.getText()),
                comboBox.getSelectionModel().getSelectedItem(),
                StubVetement.getLesVetements()
        ));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        vendeurVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        textFieldPseudo.getScene().getWindow().hide();
    }
}
