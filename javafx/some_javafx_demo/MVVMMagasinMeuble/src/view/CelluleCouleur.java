package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import viewmodel.CouleurVM;

public class CelluleCouleur extends ListCell<CouleurVM> {

    private Label label = new Label();
    private Rectangle rectangle = new Rectangle();

    @Override
    protected void updateItem(CouleurVM couleur, boolean empty) {
        super.updateItem(couleur, empty);
        if (!empty) {
            HBox hbox = new HBox();
            constructionElementCellule(hbox);
            constructionBinding(couleur);
            setGraphic(hbox);
        } else {
            destructionBinding();
            setGraphic(null);
        }
    }

    private void constructionElementCellule(HBox hbox) {
        rectangle.setWidth(30);
        rectangle.setHeight(10);
        label.setPadding(new Insets(5, 10, 5, 10));
        getStylesheets().add("/css/CelluleCouleur.css");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(rectangle, label);
    }

    private void destructionBinding() {
        rectangle.fillProperty().unbind();
        label.textProperty().unbind();
    }

    private void constructionBinding(CouleurVM couleur) {
        rectangle.fillProperty().bind(couleur.couleurProperty());
        label.textProperty().bind(Bindings.format("R: %d, V: %d, B: %d (Hexadécimal: %s)",
                couleur.rougeProperty(),
                couleur.vertProperty(),
                couleur.bleuProperty(),
                couleur.couleurProperty()
        ));
    }
}
