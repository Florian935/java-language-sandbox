package view;

import donnee.StubVoitureVM;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import viewmodel.VoitureVM;

public class FenetrePrincipale {

    private VoitureVM modelVM = StubVoitureVM.getVoitureVM();

    @FXML
    private Label labelCouleur;

    @FXML
    private ColorPicker colorPicker;

    public void initialize() {
        colorPicker.valueProperty().bindBidirectional(modelVM.couleurVoitureProperty());
        labelCouleur.textProperty().bind(colorPicker.valueProperty().asString());
    }

}
