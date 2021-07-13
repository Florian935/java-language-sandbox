package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import model.Couleur;
import viewmodel.CouleurVM;

public class AjouterCouleur {

    private CouleurVM couleurChoisie;

    @FXML
    private ColorPicker colorPicker;

    public CouleurVM getCouleurChoisie() {
        return couleurChoisie;
    }

    @FXML
    private void clicAjouter() {
        couleurChoisie = new CouleurVM(new Couleur(
                colorPicker.getValue().getRed(),
                colorPicker.getValue().getGreen(),
                colorPicker.getValue().getBlue()
                )
        );
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        couleurChoisie = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        colorPicker.getScene().getWindow().hide();
    }
}
