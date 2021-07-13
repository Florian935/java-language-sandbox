package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AjouterCouleur {

    private Color laCouleur;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label labelCouleur;

    public Color getLaCouleur() {
        return laCouleur;
    }

    @FXML
    private void clicAjouter() {
        laCouleur = colorPicker.getValue();
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        colorPicker = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        labelCouleur.getScene().getWindow().hide();
    }
}
