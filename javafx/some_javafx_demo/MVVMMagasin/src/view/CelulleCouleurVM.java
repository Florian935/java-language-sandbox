package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import viewmodel.CouleurVM;

public class CelulleCouleurVM extends ListCell<CouleurVM> {

    private Label label = new Label();
    private Rectangle rectangle = new Rectangle();

    @Override
    protected void updateItem(CouleurVM couleurVM, boolean empty) {
        super.updateItem(couleurVM, empty);
        if (!empty) {
            constructionBinding(couleurVM);
            HBox hbox = new HBox();
            constructionElementGraphique(hbox);
            setGraphic(hbox);
        } else {
            setGraphic(null);
            destructionBinding();
        }
    }

    private void constructionElementGraphique(HBox hbox) {
        rectangle.setHeight(10);
        rectangle.setWidth(50);
        hbox.setAlignment(Pos.CENTER_LEFT);
        label.setPadding(new Insets(0, 0, 0, 5));
        hbox.getChildren().addAll(rectangle, label);
    }

    private void constructionBinding(CouleurVM couleurVM) {
        label.textProperty().bind(Bindings.format("R: %s, V: %s, B: %s (Hexa: %s)",
                couleurVM.rougeProperty().asString(),
                couleurVM.vertProperty().asString(),
                couleurVM.bleuProperty().asString(),
                couleurVM.couleurProperty().asString()
        ));
        rectangle.fillProperty().bind(couleurVM.couleurProperty());
    }

    private void destructionBinding() {
        label.textProperty().unbind();
        rectangle.fillProperty().unbind();
    }
}
