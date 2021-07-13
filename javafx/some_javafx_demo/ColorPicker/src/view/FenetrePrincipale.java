package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class FenetrePrincipale {

    @FXML
    private ColorPicker colorPicker;

    public void initialize() {
        double r = 1;
        double v = 125.0 / 255.0;
        double b = 200.0 / 255.0;
        colorPicker.valueProperty().set(new Color(r,v,b,1));
    }
}
