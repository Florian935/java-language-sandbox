package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import model.Couleur;
import model.utils.RVB;
import viewmodel.CouleurVM;

public class AjouterCouleur {

    private CouleurVM couleurVM;

    @FXML
    private ColorPicker colorPicker;

    public CouleurVM getCouleurVM() {
        return couleurVM;
    }

    @FXML
    private void clicAjouter() {
        couleurVM = new CouleurVM(new Couleur(new RVB(
                colorPicker.getValue().getRed(),
                colorPicker.getValue().getGreen(),
                colorPicker.getValue().getBlue()
                )));
        fermerFenetre();
    }

    @FXML
    private void clicAnnuler() {
        couleurVM = null;
        fermerFenetre();
    }

    private void fermerFenetre() {
        colorPicker.getScene().getWindow().hide();
    }
}
