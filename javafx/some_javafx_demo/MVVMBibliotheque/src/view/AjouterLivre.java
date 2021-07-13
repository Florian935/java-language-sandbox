package view;

import donnee.StubCouleur;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Livre;
import model.Type;
import viewmodel.LivreVM;

public class AjouterLivre {

    private LivreVM livreVM;

    @FXML
    private TextField textFieldTitre;
    @FXML
    private TextField textFieldPrix;
    @FXML
    private TextField textFieldExemplaire;

    @FXML
    private ComboBox<Type> comboBoxType;

    public void initialize() {
        comboBoxType.getItems().addAll(Type.values());
    }

    @FXML
    private void clicAjouter() {
        livreVM = new LivreVM(new Livre(
                textFieldTitre.getText(),
                Float.parseFloat(textFieldPrix.getText()),
                Integer.parseInt(textFieldExemplaire.getText()),
                comboBoxType.getSelectionModel().getSelectedItem(),
                StubCouleur.getCouleurs()
        ));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        livreVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        comboBoxType.getScene().getWindow().hide();
    }

    public LivreVM getLivreVM() {
        return livreVM;
    }
}
