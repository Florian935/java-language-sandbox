package view;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import viewmodel.CouleurVM;

public class CelluleCouleur extends ListCell<CouleurVM> {

    private Rectangle rectangle = new Rectangle();
    private Label label = new Label();

    @Override
    protected void updateItem(CouleurVM couleurVM, boolean empty) {
        super.updateItem(couleurVM, empty);
        if (!empty) {
            HBox hbox = new HBox();
            initialisationPropriete(hbox);
            initialisationHoverHbox(hbox);
            constructionBinding(couleurVM);
        } else {
            destructionBinding();
            setGraphic(null);
        }
    }

    private void initialisationHoverHbox(HBox hbox) {
        hbox.hoverProperty().addListener((unused, oldV, newV) -> {
            if (oldV != null && !oldV) {
                settingDimensionRectangle(35, 15);

            }
            if (newV != null && !newV) {
                settingDimensionRectangle(25, 10);
            }
        });
    }

    private void settingDimensionRectangle(int i, int i2) {
        rectangle.setWidth(i);
        rectangle.setHeight(i2);
    }

    private void initialisationPropriete(HBox hbox) {
        rectangle.setHeight(10);
        rectangle.setWidth(25);
        getStylesheets().add("/css/CelluleCouleur.css");
        hbox.getStyleClass().add("hbox-cellule");
        getStyleClass().add("cellule");
        hbox.getChildren().addAll(rectangle, label);
        setGraphic(hbox);
    }

    private void destructionBinding() {
        label.textProperty().unbind();
        rectangle.fillProperty().unbind();
    }

    private void constructionBinding(CouleurVM couleurVM) {
        rectangle.fillProperty().bind(couleurVM.couleurProperty());
        label.textProperty().bind(Bindings.format("R: %d, V: %d, B: %d",
                couleurVM.rougeProperty(),
                couleurVM.vertProperty(),
                couleurVM.bleuProperty()
        ));
    }
}
