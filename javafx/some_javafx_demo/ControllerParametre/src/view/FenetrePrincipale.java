package view;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FenetrePrincipale {

    @FXML
    private VBox vBox;

    @FXML
    private void clicAjouterController() {
        try {
            ModeleController mc = new ModeleController("Mon controller");
            vBox.getChildren().add(mc);
        } catch (IOException e) {
            Logger.getLogger(FenetrePrincipale.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
