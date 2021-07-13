package view;

import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ModeleController extends HBox {

    @FXML
    private Label label;

    private String message;

    public ModeleController(@NamedArg("label") String message) throws IOException {
        this.message = message;
        FXMLLoader leLoader = new FXMLLoader(getClass().getResource("/fxml/ModeleController.fxml"));
        leLoader.setController(this);
        leLoader.setRoot(this);
        leLoader.load();
    }

    public void initialize() {
        label.setText(message);
    }

    @FXML
    private void clicSurCoucou() {
        label.setText("Clic sur le bouton !");
    }
}
