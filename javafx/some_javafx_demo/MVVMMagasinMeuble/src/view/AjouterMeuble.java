package view;

import donnee.StubCouleur;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Etat;
import model.Meuble;
import viewmodel.MeubleVM;

public class AjouterMeuble {

    private MeubleVM meubleVM;

    @FXML
    private ComboBox comboBoxEtat;
    @FXML
    private TextField textFieldMeuble;
    @FXML
    private TextField textFieldPrix;
    @FXML
    private TextField textFieldQuantite;

    public void initialize() {
        comboBoxEtat.getItems().addAll(Etat.values());
    }

    @FXML
    private void clicAjouter() {
        meubleVM = new MeubleVM(new Meuble(
                textFieldMeuble.getText(),
                (Etat) comboBoxEtat.getSelectionModel().getSelectedItem(),
                Integer.parseInt(textFieldQuantite.getText()),
                Float.parseFloat(textFieldPrix.getText()),
                StubCouleur.getLesCouleurs()));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        meubleVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        comboBoxEtat.getScene().getWindow().hide();
    }

    public MeubleVM getMeubleVM() {
        return meubleVM;
    }
}
