package view;

import donnee.POJOImage;
import donnee.StubImage;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Couleur;
import model.Vetement;
import model.utils.RVB;
import viewmodel.VetementVM;

public class AjouterVetement {

    private VetementVM vetementVM;

    @FXML
    private TextField textFieldNom;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ComboBox<POJOImage> comboBox;

    public void initialize() {
        comboBox.getItems().setAll(StubImage.getLesImages());
        comboBox.setCellFactory(unused -> new CelluleImage());
    }

    public VetementVM getVetementVM() {
        return vetementVM;
    }

    @FXML
    private void clicAjouter() {
        vetementVM = new VetementVM(new Vetement(
                textFieldNom.getText(),
                new Couleur(new RVB(
                        colorPicker.getValue().getRed(),
                        colorPicker.getValue().getGreen(),
                        colorPicker.getValue().getBlue()
                        )),
                comboBox.getSelectionModel().getSelectedItem().url
        ));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        vetementVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        colorPicker.getScene().getWindow().hide();
    }
}
