package view;

import donnee.StubEtudiantVM;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Sauveur;
import viewmodel.EtudiantVM;

public class FenetrePrincipale {

    private EtudiantVM modelVM = StubEtudiantVM.getEtudiantVM();

    @FXML
    private TextField textField;
    @FXML
    private Label label;

    public void initialize() {
        textField.textProperty().bindBidirectional(modelVM.nomProperty());
        label.textProperty().bind(modelVM.nomProperty());
        Sauveur.save(modelVM.getModel());
        System.out.println(Sauveur.read());
    }
}
