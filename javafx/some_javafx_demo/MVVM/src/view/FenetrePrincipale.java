package view;

import donnees.StubVoitureVM;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.VoitureVM;

public class FenetrePrincipale {

    private VoitureVM modelVM = StubVoitureVM.getVoitureVM();

    @FXML
    private TextField textFieldMarque;
    @FXML
    private Label labelMarque;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label labelCouleur;

    public void initialize() {
        constructionBinding();
    }

    private void constructionBinding() {
        textFieldMarque.textProperty().bindBidirectional(modelVM.marqueProperty());
        labelMarque.textProperty().bind(modelVM.marqueProperty());
        colorPicker.valueProperty().bindBidirectional(modelVM.couleurVoitureProperty());
        labelCouleur.textProperty().bind(modelVM.couleurVoitureProperty().asString().concat(modelVM.couleurRGBProperty()));
    }
}
