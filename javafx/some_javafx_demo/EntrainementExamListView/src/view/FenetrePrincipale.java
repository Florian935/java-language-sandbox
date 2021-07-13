package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class FenetrePrincipale {

    @FXML
    private ImageView imageView;

    public void initialize() {
        String path = "resource/images/1.png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
}
