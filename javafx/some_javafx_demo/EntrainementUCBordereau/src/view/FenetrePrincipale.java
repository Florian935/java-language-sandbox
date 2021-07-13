package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FenetrePrincipale {

    @FXML
    private Button btn;

    public void initialize() {
    }

    @FXML
    private void clicBtn1() {
        btn.textProperty().set("Comportement spécifique.");
        // TODO:
        // Trouver une bonne idée de bordereau pour l'exam. (Bordereau en haut dans une coin pour l'ajout de valeur.
        // Bonne idée ?)
    }
}
