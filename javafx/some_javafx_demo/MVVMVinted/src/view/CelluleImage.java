package view;

import donnee.POJOImage;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class CelluleImage extends ListCell<POJOImage> {

    @Override
    protected void updateItem(POJOImage image, boolean empty) {
        super.updateItem(image, empty);
        if (!empty) {
            ImageView imageView = new ImageView();
            imageView.setImage(image.image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            setGraphic(imageView);
        } else {
            setGraphic(null);
        }
    }
}
